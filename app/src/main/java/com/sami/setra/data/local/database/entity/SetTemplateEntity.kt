package com.sami.setra.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "set_template",
    foreignKeys = [
        ForeignKey(
            entity = RoutineExerciseEntity::class,
            parentColumns = ["id"],
            childColumns = ["routineExerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["routineExerciseId"])]
)
data class SetTemplateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val routineExerciseId: Long,
    val ordering: Int = 0,
    val setType: String = "NORMAL", // "NORMAL", "WARMUP", "DROP", "FAILURE"
    val targetReps: Double? = 10.0,
    val targetWeight: Double? = null,
    val durationSeconds: Int? = null,
    val restSeconds: Int? = 90,
    val targetRpe: Double? = null,
    val targetRir: Int? = null,
    val tempo: String? = null,
    val isWarmup: Boolean = false,
    val isDropSet: Boolean = false,
    val isFailureSet: Boolean = false,
    val notes: String? = null
)
