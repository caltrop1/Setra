package com.sami.setra.data.repository

import com.sami.setra.data.local.database.entity.ExerciseEntity
import com.sami.setra.data.local.database.entity.ExerciseImageEntity
import com.sami.setra.data.local.database.entity.ExerciseMuscleEntity
import com.sami.setra.data.local.database.entity.MuscleEntity
import com.sami.setra.data.local.database.relation.ExerciseMuscleWithMuscle
import com.sami.setra.data.local.database.relation.ExerciseWithDetails
import org.junit.Assert.assertEquals
import org.junit.Test

class ExerciseRepositoryTest {

    @Test
    fun exerciseWithDetails_computesMusclesAndImagesCorrectly() {
        val exercise = ExerciseEntity(
            id = "bench_press",
            name = "Barbell Bench Press",
            category = "strength",
            equipment = "barbell",
            level = "beginner",
            force = "push",
            mechanic = "compound",
            instructions = "Lie on bench. Lower barbell to chest. Press up."
        )

        val chestMuscle = MuscleEntity("chest", "chest", "Upper Body", "Chest", "Chest", 1)
        val tricepsMuscle = MuscleEntity("triceps", "triceps", "Upper Body", "Triceps", "Arms", 2)

        val images = listOf(
            ExerciseImageEntity(id = 1, exerciseId = "bench_press", assetPath = "exercises/bench_press/0.jpg", ordering = 0),
            ExerciseImageEntity(id = 2, exerciseId = "bench_press", assetPath = "exercises/bench_press/1.jpg", ordering = 1)
        )

        val exerciseMuscles = listOf(
            ExerciseMuscleWithMuscle(
                exerciseMuscle = ExerciseMuscleEntity(id = 10, exerciseId = "bench_press", muscleId = "chest", role = "PRIMARY", ordering = 0),
                muscle = chestMuscle
            ),
            ExerciseMuscleWithMuscle(
                exerciseMuscle = ExerciseMuscleEntity(id = 11, exerciseId = "bench_press", muscleId = "triceps", role = "SECONDARY", ordering = 0),
                muscle = tricepsMuscle
            )
        )

        val details = ExerciseWithDetails(
            exercise = exercise,
            images = images,
            exerciseMuscles = exerciseMuscles
        )

        assertEquals("bench_press", details.exercise.id)
        assertEquals("exercises/bench_press/0.jpg", details.primaryImagePath)
        assertEquals(1, details.primaryMuscles.size)
        assertEquals("Chest", details.primaryMuscles.first().displayName)
        assertEquals(1, details.secondaryMuscles.size)
        assertEquals("Triceps", details.secondaryMuscles.first().displayName)
    }
}
