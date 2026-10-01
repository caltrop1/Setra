package com.sami.setra.data.local.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercise",
    indices = [
        Index(value = ["name"]),
        Index(value = ["equipment"]),
        Index(value = ["category"]),
        Index(value = ["level"])
    ]
)
data class ExerciseEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val category: String,
    val equipment: String?,
    val level: String,
    val force: String?,
    val mechanic: String?,
    val instructions: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
