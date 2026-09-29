package com.sami.setra.ui.screens.create

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sami.setra.ui.components.ScreenPlaceholder

@Composable
fun CreateScreen(
    modifier: Modifier = Modifier
) {
    ScreenPlaceholder(
        title = "Create",
        subtitle = "Quickly log an ad-hoc workout or construct a new training routine from scratch.",
        badgeText = "Phase 1 Foundation",
        icon = Icons.Default.AddCircle,
        upcomingFeatures = listOf(
            "Start empty freestyle workout",
            "Create new custom routine",
            "Import routine or workout template",
            "Offline exercise selector & filter"
        ),
        modifier = modifier
    )
}
