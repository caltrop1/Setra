package com.sami.setra.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sami.setra.data.local.database.entity.MuscleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MuscleDao {

    @Query("SELECT * FROM muscle ORDER BY ordering ASC")
    fun getAllMuscles(): Flow<List<MuscleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMuscles(muscles: List<MuscleEntity>)
}
