package com.sami.setra.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sami.setra.data.local.preferences.DistanceUnit
import com.sami.setra.data.local.preferences.UserPreferences
import com.sami.setra.data.local.preferences.WeightUnit
import com.sami.setra.ui.screens.create.CreateScreen
import com.sami.setra.ui.screens.exercises.ExerciseDetailScreen
import com.sami.setra.ui.screens.exercises.ExerciseDetailViewModel
import com.sami.setra.ui.screens.exercises.ExerciseListScreen
import com.sami.setra.ui.screens.exercises.ExerciseListViewModel
import com.sami.setra.ui.screens.profile.ProfileScreen
import com.sami.setra.ui.screens.progress.ProgressScreen
import com.sami.setra.ui.screens.routines.RoutinesScreen
import com.sami.setra.ui.screens.routines.RoutinesViewModel
import com.sami.setra.ui.screens.splits.SplitDetailScreen
import com.sami.setra.ui.screens.splits.SplitsScreen
import com.sami.setra.ui.screens.splits.SplitsViewModel
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
    val splitsViewModel: SplitsViewModel = viewModel()
    val routinesViewModel: RoutinesViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splits,
        modifier = modifier
    ) {
        composable<Screen.Splits> {
            SplitsScreen(
                splitsViewModel = splitsViewModel,
                onSplitClick = { splitId ->
                    navController.navigate(Screen.SplitDetail(splitId))
                },
                onUseSplitClick = { template ->
                    splitsViewModel.useSplit(template) {
                        navController.navigateToBottomNavDestination(Screen.Routines)
                    }
                },
                onOpenExerciseLibrary = {
                    navController.navigate(Screen.ExerciseList)
                }
            )
        }

        composable<Screen.SplitDetail> { backStackEntry ->
            val detail: Screen.SplitDetail = backStackEntry.toRoute()
            val split = splitsViewModel.getSplitById(detail.splitId)
            if (split != null) {
                SplitDetailScreen(
                    split = split,
                    onBackClick = { navController.popBackStack() },
                    onUseSplitClick = {
                        splitsViewModel.useSplit(split) {
                            navController.navigateToBottomNavDestination(Screen.Routines)
                        }
                    }
                )
            }
        }

        composable<Screen.Routines> {
            RoutinesScreen(
                viewModel = routinesViewModel,
                onBrowseSplitsClick = {
                    navController.navigateToBottomNavDestination(Screen.Splits)
                }
            )
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

        composable<Screen.ExerciseList> {
            val exerciseListViewModel: ExerciseListViewModel = viewModel()
            ExerciseListScreen(
                viewModel = exerciseListViewModel,
                onExerciseClick = { exerciseId ->
                    navController.navigate(Screen.ExerciseDetail(exerciseId))
                }
            )
        }

        composable<Screen.ExerciseDetail> { backStackEntry ->
            val detail: Screen.ExerciseDetail = backStackEntry.toRoute()
            val exerciseDetailViewModel: ExerciseDetailViewModel = viewModel()
            ExerciseDetailScreen(
                exerciseId = detail.exerciseId,
                viewModel = exerciseDetailViewModel,
                onBackClick = { navController.popBackStack() }
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
