package com.sami.setra.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "routine_exercise",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutEntity::class,
            parentColumns = ["id"],
            childColumns = ["workoutId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SupersetGroupEntity::class,
            parentColumns = ["id"],
            childColumns = ["supersetGroupId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["workoutId"]),
        Index(value = ["exerciseId"]),
        Index(value = ["supersetGroupId"])
    ]
)
data class RoutineExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val workoutId: Long,
    val exerciseId: String,
    val ordering: Int = 0,
    val notes: String? = null,
    val supersetGroupId: Long? = null
)
