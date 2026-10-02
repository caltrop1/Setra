package com.sami.setra.ui.screens.splits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import com.sami.setra.data.model.SplitTemplate
import com.sami.setra.ui.components.SetraGlassSurface
import com.sami.setra.ui.components.SetraSecondaryButton
import com.sami.setra.ui.components.SetraSectionHeader
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.SetraTheme

@Composable
fun SplitsScreen(
    splitsViewModel: SplitsViewModel,
    onSplitClick: (String) -> Unit,
    onUseSplitClick: (SplitTemplate) -> Unit,
    onOpenExerciseLibrary: () -> Unit,
    modifier: Modifier = Modifier
) {
    val splits = splitsViewModel.splits

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.spaceMedium),
        contentPadding = PaddingValues(top = Dimens.spaceMedium, bottom = Dimens.spaceXXLarge),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceMedium)
    ) {
        // Hero Header Banner
        item {
            SetraGlassSurface(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.spaceLarge)
                ) {
                    Text(
                        text = "Training Splits",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.semantics { heading() }
                    )

                    Spacer(modifier = Modifier.height(Dimens.spaceXSmall))

                    Text(
                        text = "Start from a proven weekly training template. Using a split creates a user-owned copy in My Routines that you can customize.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SetraTheme.extendedColors.textMuted
                    )

                    Spacer(modifier = Modifier.height(Dimens.spaceMedium))

                    SetraSecondaryButton(
                        text = "Browse Exercise Library (870+ Exercises)",
                        onClick = onOpenExerciseLibrary
                    )
                }
            }
        }

        item {
            SetraSectionHeader(title = "Built-In Training Templates")
        }

        items(
            items = splits,
            key = { it.id }
        ) { split ->
            SplitCard(
                split = split,
                onSplitClick = { onSplitClick(split.id) },
                onUseSplitClick = { onUseSplitClick(split) }
            )
        }
    }
}
