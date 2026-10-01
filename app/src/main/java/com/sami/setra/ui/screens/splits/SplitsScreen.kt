package com.sami.setra.ui.screens.splits

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sami.setra.ui.components.ScreenPlaceholder

@Composable
fun SplitsScreen(
    onOpenExerciseLibrary: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    ScreenPlaceholder(
        title = "Splits",
        subtitle = "Organize your training routine into structured splits such as Push/Pull/Legs, Upper/Lower, or custom splits.",
        badgeText = "Exercise Library Active",
        icon = Icons.Default.GridView,
        upcomingFeatures = listOf(
            "Pre-built splits (Push/Pull/Legs, Upper/Lower, Arnold)",
            "Custom split frequency builder (3 to 7 days)",
            "Rest day scheduling and split rotation",
            "Target muscle group distribution analysis"
        ),
        actionButtonText = "Browse Exercise Library (870+ Exercises)",
        onActionClick = onOpenExerciseLibrary,
        modifier = modifier
    )
}
