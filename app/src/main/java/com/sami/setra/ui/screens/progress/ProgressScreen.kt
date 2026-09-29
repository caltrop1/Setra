package com.sami.setra.ui.screens.progress

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sami.setra.ui.components.ScreenPlaceholder

@Composable
fun ProgressScreen(
    modifier: Modifier = Modifier
) {
    ScreenPlaceholder(
        title = "Progress",
        subtitle = "Track your volume progression, personal records (1RM), consistency calendar, and workout history.",
        badgeText = "Phase 1 Foundation",
        icon = Icons.Default.BarChart,
        upcomingFeatures = listOf(
            "1RM estimation charts & personal records",
            "Weekly/Monthly total volume and set density",
            "Muscle group frequency heatmap",
            "Complete workout history log & export"
        ),
        modifier = modifier
    )
}
