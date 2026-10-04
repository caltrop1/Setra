package com.sami.setra.data.repository

import android.content.Context
import com.sami.setra.data.local.database.SetraDatabase
import com.sami.setra.data.local.database.entity.RoutineDayEntity
import com.sami.setra.data.local.database.entity.RoutineEntity
import com.sami.setra.data.local.database.entity.RoutineExerciseEntity
import com.sami.setra.data.local.database.entity.SetTemplateEntity
import com.sami.setra.data.local.database.entity.WorkoutEntity
import com.sami.setra.data.local.database.relation.RoutineWithDays
import com.sami.setra.data.model.SplitTemplate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RoutineRepository(
    private val context: Context,
    private val database: SetraDatabase = SetraDatabase.getDatabase(context)
) {
    private val routineDao = database.routineDao()

    fun getAllActiveRoutines(): Flow<List<RoutineWithDays>> {
        return routineDao.getAllActiveRoutines()
    }

    fun getRoutineById(routineId: Long): Flow<RoutineWithDays?> {
        return routineDao.getRoutineWithDaysById(routineId)
    }

    suspend fun createRoutineFromSplit(template: SplitTemplate): Long = withContext(Dispatchers.IO) {
        // 1. Create top-level RoutineEntity
        val routineEntity = RoutineEntity(
            name = "My ${template.name}",
            description = template.description,
            creatorMetadata = "Copied from Setra ${template.name} Template",
            imageSource = "BUILT_IN",
            builtInImageId = template.imageKey,
            userImageUri = null,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        val routineId = routineDao.insertRoutine(routineEntity)

        // 2. Iterate through 7 days
        template.days.forEachIndexed { index, day ->
            val dayEntity = RoutineDayEntity(
                routineId = routineId,
                dayOfWeek = day.dayOfWeek,
                ordering = index
            )
            val dayIdList = routineDao.insertRoutineDays(listOf(dayEntity))
            val dayId = dayIdList.first()

            if (!day.isRestDay && !day.workoutName.isNullOrBlank()) {
                val workoutEntity = WorkoutEntity(
                    routineDayId = dayId,
                    name = day.workoutName,
                    description = day.workoutDescription ?: "",
                    ordering = 0
                )
                val workoutIdList = routineDao.insertWorkouts(listOf(workoutEntity))
                val workoutId = workoutIdList.first()

                day.sampleExercises.forEachIndexed { exIndex, exerciseId ->
                    val routineExerciseEntity = RoutineExerciseEntity(
                        workoutId = workoutId,
                        exerciseId = exerciseId,
                        ordering = exIndex
                    )
                    val routineExerciseIdList = routineDao.insertRoutineExercises(listOf(routineExerciseEntity))
                    val routineExerciseId = routineExerciseIdList.first()

                    // Default 3 set templates for each exercise
                    val setTemplates = listOf(
                        SetTemplateEntity(routineExerciseId = routineExerciseId, ordering = 0, targetReps = 10.0, restSeconds = 90),
                        SetTemplateEntity(routineExerciseId = routineExerciseId, ordering = 1, targetReps = 10.0, restSeconds = 90),
                        SetTemplateEntity(routineExerciseId = routineExerciseId, ordering = 2, targetReps = 10.0, restSeconds = 90)
                    )
                    routineDao.insertSetTemplates(setTemplates)
                }
            }
        }

        routineId
    }

    suspend fun deleteRoutine(routineId: Long) {
        routineDao.deleteRoutine(routineId)
    }
}
