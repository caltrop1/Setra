package com.sami.setra.data.local.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.sami.setra.data.local.database.entity.ExerciseEntity
import com.sami.setra.data.local.database.entity.ExerciseImageEntity
import com.sami.setra.data.local.database.entity.ExerciseMuscleEntity
import com.sami.setra.data.local.database.entity.MuscleEntity

data class ExerciseWithDetails(
    @Embedded val exercise: ExerciseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "exerciseId"
    )
    val images: List<ExerciseImageEntity>,
    @Relation(
        entity = ExerciseMuscleEntity::class,
        parentColumn = "id",
        entityColumn = "exerciseId"
    )
    val exerciseMuscles: List<ExerciseMuscleWithMuscle>
) {
    val primaryMuscles: List<MuscleEntity>
        get() = exerciseMuscles.filter { it.exerciseMuscle.role == "PRIMARY" }.map { it.muscle }

    val secondaryMuscles: List<MuscleEntity>
        get() = exerciseMuscles.filter { it.exerciseMuscle.role == "SECONDARY" }.map { it.muscle }

    val primaryImagePath: String?
        get() = images.sortedBy { it.ordering }.firstOrNull()?.assetPath
}
