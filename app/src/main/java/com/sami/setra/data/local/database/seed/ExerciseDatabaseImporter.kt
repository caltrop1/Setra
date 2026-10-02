package com.sami.setra.data.local.database.seed

import android.content.Context
import com.sami.setra.data.local.database.SetraDatabase
import com.sami.setra.data.local.database.entity.ExerciseEntity
import com.sami.setra.data.local.database.entity.ExerciseImageEntity
import com.sami.setra.data.local.database.entity.ExerciseMuscleEntity
import com.sami.setra.data.local.database.entity.MuscleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
private data class RawExercise(
    val id: String,
    val name: String,
    val category: String,
    val level: String,
    val force: String? = null,
    val mechanic: String? = null,
    val equipment: String? = null,
    val primaryMuscles: List<String> = emptyList(),
    val secondaryMuscles: List<String> = emptyList(),
    val instructions: List<String> = emptyList(),
    val images: List<String> = emptyList()
)

object ExerciseDatabaseImporter {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val predefinedMuscles = listOf(
        MuscleEntity("abdominals", "abdominals", "Core", "Abs", "Abs", 1),
        MuscleEntity("chest", "chest", "Upper Body", "Chest", "Chest", 2),
        MuscleEntity("shoulders", "shoulders", "Upper Body", "Shoulders", "Shoulders", 3),
        MuscleEntity("biceps", "biceps", "Upper Body", "Biceps", "Arms", 4),
        MuscleEntity("triceps", "triceps", "Upper Body", "Triceps", "Arms", 5),
        MuscleEntity("forearms", "forearms", "Upper Body", "Forearms", "Arms", 6),
        MuscleEntity("lats", "lats", "Upper Body", "Lats", "Back", 7),
        MuscleEntity("middle back", "middle back", "Upper Body", "Middle Back", "Back", 8),
        MuscleEntity("lower back", "lower back", "Core", "Lower Back", "Back", 9),
        MuscleEntity("traps", "traps", "Upper Body", "Traps", "Back", 10),
        MuscleEntity("quadriceps", "quadriceps", "Lower Body", "Quads", "Legs", 11),
        MuscleEntity("hamstrings", "hamstrings", "Lower Body", "Hamstrings", "Legs", 12),
        MuscleEntity("glutes", "glutes", "Lower Body", "Glutes", "Legs", 13),
        MuscleEntity("calves", "calves", "Lower Body", "Calves", "Legs", 14),
        MuscleEntity("abductors", "abductors", "Lower Body", "Abductors", "Legs", 15),
        MuscleEntity("adductors", "adductors", "Lower Body", "Adductors", "Legs", 16),
        MuscleEntity("neck", "neck", "Upper Body", "Neck", "Neck", 17)
    )

    suspend fun seedIfNeeded(context: Context, database: SetraDatabase) = withContext(Dispatchers.IO) {
        val exerciseDao = database.exerciseDao()
        val muscleDao = database.muscleDao()

        val existingCount = exerciseDao.getExercisesCount()
        if (existingCount > 0) return@withContext

        // 1. Seed Muscles
        muscleDao.insertMuscles(predefinedMuscles)

        // 2. Read and parse exercises.json
        val jsonString = context.assets.open("exercises.json").bufferedReader().use { it.readText() }
        val rawExercises = json.decodeFromString<List<RawExercise>>(jsonString)

        val exerciseEntities = mutableListOf<ExerciseEntity>()
        val imageEntities = mutableListOf<ExerciseImageEntity>()
        val muscleEntities = mutableListOf<ExerciseMuscleEntity>()

        val muscleMap = predefinedMuscles.associateBy { it.id }

        rawExercises.forEach { raw ->
            val instructionsJoined = raw.instructions.joinToString("\n\n")

            exerciseEntities.add(
                ExerciseEntity(
                    id = raw.id,
                    name = raw.name,
                    category = raw.category,
                    equipment = raw.equipment,
                    level = raw.level,
                    force = raw.force,
                    mechanic = raw.mechanic,
                    instructions = instructionsJoined
                )
            )

            // Images
            raw.images.forEachIndexed { index, imgRelPath ->
                imageEntities.add(
                    ExerciseImageEntity(
                        exerciseId = raw.id,
                        assetPath = "exercises/$imgRelPath",
                        ordering = index,
                        imageType = if (index == 0) "PRIMARY" else "SECONDARY"
                    )
                )
            }

            // Primary Muscles
            raw.primaryMuscles.forEachIndexed { index, muscleId ->
                if (muscleMap.containsKey(muscleId)) {
                    muscleEntities.add(
                        ExerciseMuscleEntity(
                            exerciseId = raw.id,
                            muscleId = muscleId,
                            role = "PRIMARY",
                            ordering = index
                        )
                    )
                }
            }

            // Secondary Muscles
            raw.secondaryMuscles.forEachIndexed { index, muscleId ->
                if (muscleMap.containsKey(muscleId)) {
                    muscleEntities.add(
                        ExerciseMuscleEntity(
                            exerciseId = raw.id,
                            muscleId = muscleId,
                            role = "SECONDARY",
                            ordering = index
                        )
                    )
                }
            }
        }

        // Insert in bulk transactions
        exerciseDao.insertExercises(exerciseEntities)
        exerciseDao.insertExerciseImages(imageEntities)
        exerciseDao.insertExerciseMuscles(muscleEntities)
    }
}
