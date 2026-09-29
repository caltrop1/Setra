package com.sami.setra

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sami.setra.data.local.database.SetraDatabase
import com.sami.setra.data.local.database.entity.AppMetaEntity
import com.sami.setra.data.local.preferences.DistanceUnit
import com.sami.setra.data.local.preferences.UserPreferences
import com.sami.setra.data.local.preferences.UserPreferencesRepository
import com.sami.setra.data.local.preferences.WeightUnit
import com.sami.setra.ui.theme.ThemeMode
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val userPreferencesRepository = UserPreferencesRepository(application)
    private val database = SetraDatabase.getDatabase(application)

    val userPreferences: StateFlow<UserPreferences> = userPreferencesRepository.userPreferencesFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserPreferences()
        )

    init {
        // Initialize Room database app metadata
        viewModelScope.launch {
            database.appMetaDao().insertAppMeta(
                AppMetaEntity(
                    installedTimestamp = System.currentTimeMillis(),
                    appVersion = "1.0.0"
                )
            )
        }
    }

    fun setThemeMode(themeMode: ThemeMode) {
        viewModelScope.launch {
            userPreferencesRepository.setThemeMode(themeMode)
        }
    }

    fun setWeightUnit(weightUnit: WeightUnit) {
        viewModelScope.launch {
            userPreferencesRepository.setWeightUnit(weightUnit)
        }
    }

    fun setDistanceUnit(distanceUnit: DistanceUnit) {
        viewModelScope.launch {
            userPreferencesRepository.setDistanceUnit(distanceUnit)
        }
    }
}
