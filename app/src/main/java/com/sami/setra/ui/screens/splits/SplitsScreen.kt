package com.sami.setra.ui.screens.splits

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChevronRight

import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sami.setra.data.model.SplitTemplate
import com.sami.setra.ui.components.SetraCard
import com.sami.setra.ui.components.SetraPillToggle
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.SetraTheme
import com.sami.setra.ui.theme.SplitTealAccent

@Composable
fun SplitsScreen(
    splitsViewModel: SplitsViewModel,
    onSplitClick: (String) -> Unit,
    onOpenSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val splits = splitsViewModel.splits
    var selectedToggleIndex by remember { mutableIntStateOf(0) }

    val statusBarTop = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.spaceMedium),
        contentPadding = PaddingValues(
            top = statusBarTop,
            bottom = Dimens.spaceXXLarge
        ),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceMedium)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .semantics { heading() }
                ) {
                    Text(
                        text = "Splits",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Build your strength. One split at a time.",
                        style = MaterialTheme.typography.bodySmall,
                        color = SetraTheme.extendedColors.textMuted
                    )
                }

                Spacer(modifier = Modifier.width(Dimens.spaceMedium))

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = onOpenSearch) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search exercises or splits",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(Dimens.iconMedium)
                        )
                    }
                }
            }
        }

        // Segmented Pill Toggle: [ Browse ]  [ My Splits ]
        item {
            SetraPillToggle(
                options = listOf("Browse", "My Splits"),
                selectedIndex = selectedToggleIndex,
                onOptionSelected = { selectedToggleIndex = it }
            )
        }

        // Section Title: Popular Splits
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(Dimens.iconMedium)
                    )
                    Spacer(modifier = Modifier.width(Dimens.spaceXSmall))
                    Text(
                        text = "Popular Splits",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Text(
                    text = "See all >",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { }
                )
            }
        }

        // Split Cards
        items(
            items = splits.filter { it.id != "custom" },
            key = { it.id }
        ) { split ->
            SplitCard(
                split = split,
                onSplitClick = { onSplitClick(split.id) }
            )
        }

        // Custom Split Bottom Card
        item {
            SetraCard(
                onClick = { onSplitClick("custom") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(Dimens.radiusMedium))
                                .background(SplitTealAccent.copy(alpha = 0.18f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = SplitTealAccent,
                                modifier = Modifier.size(Dimens.iconMedium)
                            )
                        }

                        Spacer(modifier = Modifier.width(Dimens.spaceMedium))

                        Column {
                            Text(
                                text = "Want something custom?",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Create your own routine or import a plan.",
                                style = MaterialTheme.typography.bodySmall,
                                color = SetraTheme.extendedColors.textMuted
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = SetraTheme.extendedColors.textMuted,
                        modifier = Modifier.size(Dimens.iconMedium)
                    )
                }
            }
        }
    }
}
