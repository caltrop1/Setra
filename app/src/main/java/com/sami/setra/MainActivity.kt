package com.sami.setra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sami.setra.navigation.Screen
import com.sami.setra.navigation.SetraNavHost
import com.sami.setra.navigation.bottomNavItems
import com.sami.setra.navigation.navigateToBottomNavDestination
import com.sami.setra.ui.components.SetraScaffold
import com.sami.setra.ui.components.SetraTopBar
import com.sami.setra.ui.theme.SetraTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val userPreferences by viewModel.userPreferences.collectAsStateWithLifecycle()

            SetraTheme(themeMode = userPreferences.themeMode) {
                SetraApp(
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun SetraApp(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val userPreferences by viewModel.userPreferences.collectAsStateWithLifecycle()

    // Determine current destination based on route class name
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination: Screen = when {
        currentRoute?.contains("Routines") == true -> Screen.Routines
        currentRoute?.contains("Create") == true -> Screen.Create
        currentRoute?.contains("Progress") == true -> Screen.Progress
        currentRoute?.contains("Profile") == true -> Screen.Profile
        else -> Screen.Splits
    }

    val screenTitle = when (currentDestination) {
        Screen.Splits -> "Splits"
        Screen.Routines -> "My Routines"
        Screen.Create -> "Create"
        Screen.Progress -> "Progress"
        Screen.Profile -> "Profile"
    }

    SetraScaffold(
        currentDestination = currentDestination,
        bottomNavItems = bottomNavItems,
        onNavigateToDestination = { destination ->
            navController.navigateToBottomNavDestination(destination)
        },
        topBar = {
            SetraTopBar(
                title = screenTitle,
                subtitle = "Setra • Offline Workout Tracker"
            )
        },
        modifier = modifier
    ) { innerPadding ->
        SetraNavHost(
            navController = navController,
            userPreferences = userPreferences,
            onThemeModeChange = viewModel::setThemeMode,
            onWeightUnitChange = viewModel::setWeightUnit,
            onDistanceUnitChange = viewModel::setDistanceUnit,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
