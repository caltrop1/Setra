package com.sami.setra.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine")
data class RoutineEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val creatorMetadata: String? = null,
    val sharedByMetadata: String? = null,
    val isArchived: Boolean = false,
    val imageSource: String = "BUILT_IN",
    val builtInImageId: String? = null,
    val userImageUri: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
