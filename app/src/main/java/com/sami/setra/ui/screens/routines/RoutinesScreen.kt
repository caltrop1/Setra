package com.sami.setra.ui.screens.routines

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sami.setra.ui.components.ScreenPlaceholder

@Composable
fun RoutinesScreen(
    modifier: Modifier = Modifier
) {
    ScreenPlaceholder(
        title = "My Routines",
        subtitle = "Build and save workout routines tailored to your goals. Fast access to exercise order, targets, and notes.",
        badgeText = "Phase 1 Foundation",
        icon = Icons.AutoMirrored.Filled.FormatListBulleted,
        upcomingFeatures = listOf(
            "Personal routine library with custom tags",
            "Exercise reordering, superset grouping, and rest timers",
            "Routine duplicator and quick launch",
            "Shareable routine templates"
        ),
        modifier = modifier
    )
}
