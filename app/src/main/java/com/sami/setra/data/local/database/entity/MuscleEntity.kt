package com.sami.setra.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "muscle")
data class MuscleEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val bodyRegion: String,
    val displayName: String,
    val displayGroup: String,
    val ordering: Int
)
