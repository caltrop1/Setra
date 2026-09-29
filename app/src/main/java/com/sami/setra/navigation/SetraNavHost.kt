package com.sami.setra.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sami.setra.data.local.preferences.DistanceUnit
import com.sami.setra.data.local.preferences.UserPreferences
import com.sami.setra.data.local.preferences.WeightUnit
import com.sami.setra.ui.screens.create.CreateScreen
import com.sami.setra.ui.screens.profile.ProfileScreen
import com.sami.setra.ui.screens.progress.ProgressScreen
import com.sami.setra.ui.screens.routines.RoutinesScreen
import com.sami.setra.ui.screens.splits.SplitsScreen
import com.sami.setra.ui.theme.ThemeMode

@Composable
fun SetraNavHost(
    navController: NavHostController,
    userPreferences: UserPreferences,
    onThemeModeChange: (ThemeMode) -> Unit,
    onWeightUnitChange: (WeightUnit) -> Unit,
    onDistanceUnitChange: (DistanceUnit) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splits,
        modifier = modifier
    ) {
        composable<Screen.Splits> {
            SplitsScreen()
        }
        composable<Screen.Routines> {
            RoutinesScreen()
        }
        composable<Screen.Create> {
            CreateScreen()
        }
        composable<Screen.Progress> {
            ProgressScreen()
        }
        composable<Screen.Profile> {
            ProfileScreen(
                userPreferences = userPreferences,
                onThemeModeChange = onThemeModeChange,
                onWeightUnitChange = onWeightUnitChange,
                onDistanceUnitChange = onDistanceUnitChange
            )
        }
    }
}

fun NavHostController.navigateToBottomNavDestination(destination: Screen) {
    navigate(destination) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
