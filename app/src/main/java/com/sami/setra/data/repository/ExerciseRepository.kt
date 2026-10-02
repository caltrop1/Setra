package com.sami.setra.data.repository

import android.content.Context
import com.sami.setra.data.local.database.SetraDatabase
import com.sami.setra.data.local.database.entity.MuscleEntity
import com.sami.setra.data.local.database.relation.ExerciseWithDetails
import com.sami.setra.data.local.database.seed.ExerciseDatabaseImporter
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(
    private val context: Context,
    private val database: SetraDatabase = SetraDatabase.getDatabase(context)
) {
    private val exerciseDao = database.exerciseDao()
    private val muscleDao = database.muscleDao()

    suspend fun seedIfNeeded() {
        ExerciseDatabaseImporter.seedIfNeeded(context, database)
    }

    fun searchExercises(
        query: String? = null,
        equipment: String? = null,
        muscleId: String? = null,
        category: String? = null,
        level: String? = null
    ): Flow<List<ExerciseWithDetails>> {
        return exerciseDao.searchExercises(
            query = query,
            equipment = equipment,
            muscleId = muscleId,
            category = category,
            level = level
        )
    }

    fun getExerciseById(exerciseId: String): Flow<ExerciseWithDetails?> {
        return exerciseDao.getExerciseById(exerciseId)
    }

    fun getAllMuscles(): Flow<List<MuscleEntity>> {
        return muscleDao.getAllMuscles()
    }

    fun getAllEquipment(): Flow<List<String>> {
        return exerciseDao.getAllEquipment()
    }

    fun getAllCategories(): Flow<List<String>> {
        return exerciseDao.getAllCategories()
    }

    fun getAllLevels(): Flow<List<String>> {
        return exerciseDao.getAllLevels()
    }
}
