package com.sami.setra.data.local.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.sami.setra.data.local.database.entity.ExerciseMuscleEntity
import com.sami.setra.data.local.database.entity.MuscleEntity

data class ExerciseMuscleWithMuscle(
    @Embedded val exerciseMuscle: ExerciseMuscleEntity,
    @Relation(
        parentColumn = "muscleId",
        entityColumn = "id"
    )
    val muscle: MuscleEntity
)
