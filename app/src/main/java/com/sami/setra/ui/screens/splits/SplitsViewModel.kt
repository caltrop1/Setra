package com.sami.setra.ui.screens.splits

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sami.setra.data.model.SplitTemplate
import com.sami.setra.data.repository.RoutineRepository
import com.sami.setra.data.repository.SplitTemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplitsViewModel(application: Application) : AndroidViewModel(application) {

    private val splitTemplateRepository = SplitTemplateRepository()
    private val routineRepository = RoutineRepository(application)

    val splits: List<SplitTemplate> = splitTemplateRepository.builtInSplits

    private val _isCopying = MutableStateFlow(false)
    val isCopying: StateFlow<Boolean> = _isCopying.asStateFlow()

    fun getSplitById(splitId: String): SplitTemplate? {
        return splitTemplateRepository.getSplitById(splitId)
    }

    fun useSplit(template: SplitTemplate, onRoutineCreated: (Long) -> Unit) {
        viewModelScope.launch {
            _isCopying.value = true
            val newRoutineId = routineRepository.createRoutineFromSplit(template)
            _isCopying.value = false
            onRoutineCreated(newRoutineId)
        }
    }
}
