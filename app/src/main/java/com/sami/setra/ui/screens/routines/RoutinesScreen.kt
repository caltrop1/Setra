package com.sami.setra.ui.screens.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sami.setra.ui.components.SetraCard
import com.sami.setra.ui.components.SetraGlassSurface
import com.sami.setra.ui.components.SetraPrimaryButton
import com.sami.setra.ui.components.SetraSectionHeader
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.SetraTheme

@Composable
fun RoutinesScreen(
    viewModel: RoutinesViewModel,
    onBrowseSplitsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val routines by viewModel.routines.collectAsStateWithLifecycle()

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
                        text = "My Routines",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.semantics { heading() }
                    )

                    Spacer(modifier = Modifier.height(Dimens.spaceXSmall))

                    Text(
                        text = "Your personal user-owned routines created from training splits or custom creation.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SetraTheme.extendedColors.textMuted
                    )
                }
            }
        }

        item {
            SetraSectionHeader(title = "User Routines (${routines.size})")
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
                val routine = routineWithDays.routine
                val days = routineWithDays.routineDays.sortedBy { it.routineDay.dayOfWeek }

                SetraCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = routine.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${routineWithDays.activeDaysCount} Training Days • ${routineWithDays.restDaysCount} Rest Days",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                            IconButton(onClick = { viewModel.deleteRoutine(routine.id) }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete routine",
                                    tint = SetraTheme.extendedColors.textMuted
                                )
                            }
                        }

                        if (routine.description.isNotBlank()) {
                            Spacer(modifier = Modifier.height(Dimens.spaceXSmall))
                            Text(
                                text = routine.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = SetraTheme.extendedColors.textMuted
                            )
                        }

                        Spacer(modifier = Modifier.height(Dimens.spaceMedium))

                        // 7-day schedule summary
                        Column(
                            verticalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.small)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(Dimens.spaceSmall)
                        ) {
                            val dayNames = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                            dayNames.forEachIndexed { index, dayName ->
                                val dayNum = index + 1
                                val dayData = days.find { it.routineDay.dayOfWeek == dayNum }
                                val workout = dayData?.workouts?.firstOrNull()

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = dayName,
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = if (workout == null) SetraTheme.extendedColors.textMuted else MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.width(40.dp)
                                    )

                                    Text(
                                        text = workout?.workout?.name ?: "Rest Day",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = if (workout == null) FontWeight.Normal else FontWeight.Medium,
                                        color = if (workout == null) SetraTheme.extendedColors.textMuted else MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
