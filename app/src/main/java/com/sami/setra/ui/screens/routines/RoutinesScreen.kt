package com.sami.setra.ui.screens.routines

import androidx.compose.foundation.background
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

import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sami.setra.ui.components.SetraCard
import com.sami.setra.ui.components.SetraPillToggle
import com.sami.setra.ui.components.SetraPrimaryButton
import com.sami.setra.ui.theme.ButtonShape
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.SetraTheme

@Composable
fun RoutinesScreen(
    viewModel: RoutinesViewModel,
    onBrowseSplitsClick: () -> Unit,
    modifier: Modifier = Modifier,
    onOpenSearch: () -> Unit = {}
) {
    val routines by viewModel.routines.collectAsStateWithLifecycle()
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .semantics { heading() }
                ) {
                    Text(
                        text = "My Routines",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Your saved routines and custom plans.",
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
                            contentDescription = "Search routines",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(Dimens.iconMedium)
                        )
                    }
                }
            }
        }

        // Segmented Pill Toggle: [ My Routines ]  [ Created by Me ]
        item {
            SetraPillToggle(
                options = listOf("My Routines", "Created by Me"),
                selectedIndex = selectedToggleIndex,
                onOptionSelected = { selectedToggleIndex = it }
            )
        }

        // Saved Routines Count & + Create Routine Button
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Saved Routines (${routines.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Button(
                    onClick = onBrowseSplitsClick,
                    shape = ButtonShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    contentPadding = PaddingValues(horizontal = Dimens.spaceMedium, vertical = Dimens.spaceXSmall),
                    modifier = Modifier.height(38.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(Dimens.spaceXSmall))
                    Text(
                        text = "Create Routine",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (routines.isEmpty()) {
            item {
                SetraCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimens.spaceLarge)
                    ) {
                        Text(
                            text = "No routines created yet",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(Dimens.spaceSmall))
                        Text(
                            text = "Choose a built-in split from the Splits tab to create your first routine.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = SetraTheme.extendedColors.textMuted
                        )
                        Spacer(modifier = Modifier.height(Dimens.spaceLarge))
                        SetraPrimaryButton(
                            text = "Browse Training Splits",
                            onClick = onBrowseSplitsClick,
                            modifier = Modifier.width(220.dp)
                        )
                    }
                }
            }
        } else {
            items(
                items = routines,
                key = { it.routine.id }
            ) { routineWithDays ->
                RoutineCard(
                    routineWithDays = routineWithDays,
                    onDeleteClick = { viewModel.deleteRoutine(routineWithDays.routine.id) }
                )
            }

            // Bottom Empty / Call-to-action Banner matching mockups
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Dimens.spaceXLarge, bottom = Dimens.spaceMedium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FolderOpen,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(Dimens.iconLarge)
                        )
                    }

                    Spacer(modifier = Modifier.height(Dimens.spaceSmall))

                    Text(
                        text = "No more routines?",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(Dimens.spaceXSmall))

                    Text(
                        text = "Create your own or import a template\nfrom a friend.",
                        style = MaterialTheme.typography.bodySmall,
                        color = SetraTheme.extendedColors.textMuted,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(Dimens.spaceMedium))

                    SetraPrimaryButton(
                        text = "+ Create Routine",
                        onClick = onBrowseSplitsClick,
                        modifier = Modifier.width(220.dp)
                    )
                }
            }
        }
    }
}
