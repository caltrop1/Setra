package com.sami.setra.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import com.sami.setra.data.local.preferences.DistanceUnit
import com.sami.setra.data.local.preferences.UserPreferences
import com.sami.setra.data.local.preferences.WeightUnit
import com.sami.setra.ui.components.SetraCard
import com.sami.setra.ui.components.SetraGlassSurface
import com.sami.setra.ui.components.SetraSectionHeader
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.SetraTheme
import com.sami.setra.ui.theme.ThemeMode

@Composable
fun ProfileScreen(
    userPreferences: UserPreferences,
    onThemeModeChange: (ThemeMode) -> Unit,
    onWeightUnitChange: (WeightUnit) -> Unit,
    onDistanceUnitChange: (DistanceUnit) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.spaceMedium, vertical = Dimens.spaceMedium)
    ) {
        // App Header Banner
        SetraGlassSurface(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.spaceLarge)
            ) {
                Text(
                    text = "Profile & Preferences",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.semantics { heading() }
                )
                Spacer(modifier = Modifier.height(Dimens.spaceXSmall))
                Text(
                    text = "Setra is 100% offline-first. Your preferences are stored locally on your device.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = SetraTheme.extendedColors.textMuted
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spaceLarge))

        // Appearance / Theme Section
        SetraSectionHeader(title = "Appearance")

        SetraCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Theme Mode",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(Dimens.spaceSmall))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ThemeMode.entries.forEach { mode ->
                        val isSelected = userPreferences.themeMode == mode
                        FilterChip(
                            selected = isSelected,
                            onClick = { onThemeModeChange(mode) },
                            label = {
                                Text(
                                    text = mode.name.lowercase().replaceFirstChar { it.uppercase() },
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spaceLarge))

        // Units Section
        SetraSectionHeader(title = "Units & Formatting")

        SetraCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Weight Unit",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(Dimens.spaceSmall))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WeightUnit.entries.forEach { unit ->
                        val isSelected = userPreferences.weightUnit == unit
                        FilterChip(
                            selected = isSelected,
                            onClick = { onWeightUnitChange(unit) },
                            label = {
                                Text(
                                    text = unit.name,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Dimens.spaceMedium))

                Text(
                    text = "Distance Unit",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(Dimens.spaceSmall))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DistanceUnit.entries.forEach { unit ->
                        val isSelected = userPreferences.distanceUnit == unit
                        FilterChip(
                            selected = isSelected,
                            onClick = { onDistanceUnitChange(unit) },
                            label = {
                                Text(
                                    text = unit.name,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spaceLarge))

        // About / System Info
        SetraSectionHeader(title = "App Information")

        SetraCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Setra Version",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "1.0.0 (Phase 1)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SetraTheme.extendedColors.textMuted
                    )
                }

                Spacer(modifier = Modifier.height(Dimens.spaceSmall))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Storage & Privacy",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "100% Local (Room & DataStore)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spaceXXLarge))
    }
}
