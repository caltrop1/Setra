package com.sami.setra.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sami.setra.navigation.BottomNavItem
import com.sami.setra.navigation.Screen

@Composable
fun SetraScaffold(
    currentDestination: Screen,
    bottomNavItems: List<BottomNavItem>,
    onNavigateToDestination: (Screen) -> Unit,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = containerColor,
        topBar = topBar,
        bottomBar = {
            SetraBottomNavigation(
                currentDestination = currentDestination,
                items = bottomNavItems,
                onNavigateToDestination = onNavigateToDestination
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        content(innerPadding)
    }
}
