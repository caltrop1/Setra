package com.sami.setra.ui.screens.exercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sami.setra.data.local.database.relation.ExerciseWithDetails
import com.sami.setra.data.repository.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExerciseDetailViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = ExerciseRepository(application)

    private val _exerciseDetails = MutableStateFlow<ExerciseWithDetails?>(null)
    val exerciseDetails: StateFlow<ExerciseWithDetails?> = _exerciseDetails.asStateFlow()

    fun loadExercise(exerciseId: String) {
        viewModelScope.launch {
            repository.getExerciseById(exerciseId).collect { details ->
                _exerciseDetails.value = details
            }
        }
    }
}
