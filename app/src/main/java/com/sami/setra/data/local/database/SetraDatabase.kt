package com.sami.setra.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sami.setra.data.local.database.dao.AppMetaDao
import com.sami.setra.data.local.database.entity.AppMetaEntity

@Database(
    entities = [AppMetaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SetraDatabase : RoomDatabase() {

    abstract fun appMetaDao(): AppMetaDao

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
