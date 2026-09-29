package com.sami.setra.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sami.setra.data.local.database.entity.AppMetaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppMetaDao {

    @Query("SELECT * FROM app_meta WHERE id = 1")
    fun getAppMeta(): Flow<AppMetaEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppMeta(appMeta: AppMetaEntity)
}
