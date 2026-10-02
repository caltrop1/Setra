package com.sami.setra.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sami.setra.data.local.database.entity.RoutineDayEntity
import com.sami.setra.data.local.database.entity.RoutineEntity
import com.sami.setra.data.local.database.entity.RoutineExerciseEntity
import com.sami.setra.data.local.database.entity.SetTemplateEntity
import com.sami.setra.data.local.database.entity.WorkoutEntity
import com.sami.setra.data.local.database.relation.RoutineWithDays
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutine(routine: RoutineEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutineDays(routineDays: List<RoutineDayEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkouts(workouts: List<WorkoutEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutineExercises(routineExercises: List<RoutineExerciseEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSetTemplates(setTemplates: List<SetTemplateEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM routine WHERE isArchived = 0 ORDER BY createdAt DESC")
    fun getAllActiveRoutines(): Flow<List<RoutineWithDays>>

    @Transaction
    @Query("SELECT * FROM routine WHERE id = :routineId LIMIT 1")
    fun getRoutineWithDaysById(routineId: Long): Flow<RoutineWithDays?>

    @Query("DELETE FROM routine WHERE id = :routineId")
    suspend fun deleteRoutine(routineId: Long)
}
