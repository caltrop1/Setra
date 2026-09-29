package com.sami.setra.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_meta")
data class AppMetaEntity(
    @PrimaryKey
    val id: Int = 1,
    val installedTimestamp: Long = System.currentTimeMillis(),
    val appVersion: String = "1.0.0"
)
