package com.sami.setra.ui.screens.exercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sami.setra.data.local.database.entity.MuscleEntity
import com.sami.setra.data.local.database.relation.ExerciseWithDetails
import com.sami.setra.data.repository.ExerciseRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ExerciseFilterState(
    val searchQuery: String = "",
    val selectedEquipment: String? = null,
    val selectedMuscleId: String? = null,
    val selectedCategory: String? = null,
    val selectedLevel: String? = null
) {
    val hasActiveFilters: Boolean
        get() = searchQuery.isNotBlank() ||
                selectedEquipment != null ||
                selectedMuscleId != null ||
                selectedCategory != null ||
                selectedLevel != null
}

class ExerciseListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ExerciseRepository(application)

    private val _filterState = MutableStateFlow(ExerciseFilterState())
    val filterState: StateFlow<ExerciseFilterState> = _filterState.asStateFlow()

    private val _isSeeding = MutableStateFlow(true)
    val isSeeding: StateFlow<Boolean> = _isSeeding.asStateFlow()

    val availableMuscles: StateFlow<List<MuscleEntity>> = repository.getAllMuscles()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val availableEquipment: StateFlow<List<String>> = repository.getAllEquipment()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val availableCategories: StateFlow<List<String>> = repository.getAllCategories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val availableLevels: StateFlow<List<String>> = repository.getAllLevels()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val exercises: StateFlow<List<ExerciseWithDetails>> = _filterState
        .flatMapLatest { filter ->
            repository.searchExercises(
                query = filter.searchQuery,
                equipment = filter.selectedEquipment,
                muscleId = filter.selectedMuscleId,
                category = filter.selectedCategory,
                level = filter.selectedLevel
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            _isSeeding.value = true
            repository.seedIfNeeded()
            _isSeeding.value = false
        }
    }

    fun onSearchQueryChange(query: String) {
        _filterState.update { it.copy(searchQuery = query) }
    }

    fun onSelectEquipment(equipment: String?) {
        _filterState.update {
            it.copy(selectedEquipment = if (it.selectedEquipment == equipment) null else equipment)
        }
    }

    fun onSelectMuscle(muscleId: String?) {
        _filterState.update {
            it.copy(selectedMuscleId = if (it.selectedMuscleId == muscleId) null else muscleId)
        }
    }

    fun onSelectCategory(category: String?) {
        _filterState.update {
            it.copy(selectedCategory = if (it.selectedCategory == category) null else category)
        }
    }

    fun onSelectLevel(level: String?) {
        _filterState.update {
            it.copy(selectedLevel = if (it.selectedLevel == level) null else level)
        }
    }

    fun clearFilters() {
        _filterState.value = ExerciseFilterState()
    }
}
