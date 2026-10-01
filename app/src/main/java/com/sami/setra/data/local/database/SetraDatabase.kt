package com.sami.setra.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sami.setra.data.local.database.dao.AppMetaDao
import com.sami.setra.data.local.database.dao.ExerciseDao
import com.sami.setra.data.local.database.dao.MuscleDao
import com.sami.setra.data.local.database.entity.AppMetaEntity
import com.sami.setra.data.local.database.entity.ExerciseEntity
import com.sami.setra.data.local.database.entity.ExerciseImageEntity
import com.sami.setra.data.local.database.entity.ExerciseMuscleEntity
import com.sami.setra.data.local.database.entity.MuscleEntity

@Database(
    entities = [
        AppMetaEntity::class,
        ExerciseEntity::class,
        ExerciseImageEntity::class,
        MuscleEntity::class,
        ExerciseMuscleEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class SetraDatabase : RoomDatabase() {

    abstract fun appMetaDao(): AppMetaDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun muscleDao(): MuscleDao

    companion object {
        @Volatile
        private var INSTANCE: SetraDatabase? = null

        fun getDatabase(context: Context): SetraDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SetraDatabase::class.java,
                    "setra_database"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
