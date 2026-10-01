package com.sami.setra.ui.screens.exercises

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sami.setra.ui.components.SetraPrimaryButton
import com.sami.setra.ui.components.SetraSectionHeader
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.SetraTheme

@Composable
fun ExerciseListScreen(
    viewModel: ExerciseListViewModel,
    onExerciseClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()
    val exercises by viewModel.exercises.collectAsStateWithLifecycle()
    val isSeeding by viewModel.isSeeding.collectAsStateWithLifecycle()

    val muscles by viewModel.availableMuscles.collectAsStateWithLifecycle()
    val equipments by viewModel.availableEquipment.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.spaceMedium)
    ) {
        Spacer(modifier = Modifier.height(Dimens.spaceMedium))

        // Search Bar
        OutlinedTextField(
            value = filterState.searchQuery,
            onValueChange = viewModel::onSearchQueryChange,
            placeholder = {
                Text(
                    text = "Search exercises...",
                    color = SetraTheme.extendedColors.textMuted
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            trailingIcon = {
                if (filterState.searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear search",
                            tint = SetraTheme.extendedColors.textMuted
                        )
                    }
                }
            },
            singleLine = true,
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Dimens.spaceSmall))

        // Filter Chips Bar (Muscle / Equipment)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Muscles filter chips
            muscles.forEach { muscle ->
                val isSelected = filterState.selectedMuscleId == muscle.id
                FilterChip(
                    selected = isSelected,
                    onClick = { viewModel.onSelectMuscle(muscle.id) },
                    label = { Text(text = muscle.displayName) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }

            // Equipment filter chips
            equipments.forEach { equipment ->
                val isSelected = filterState.selectedEquipment == equipment
                FilterChip(
                    selected = isSelected,
                    onClick = { viewModel.onSelectEquipment(equipment) },
                    label = { Text(text = equipment.lowercase().replaceFirstChar { it.uppercase() }) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                        selectedLabelColor = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spaceSmall))

        // Header / Count bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${exercises.size} Exercises",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            if (filterState.hasActiveFilters) {
                SetraSectionHeader(
                    title = "",
                    actionText = "Clear Filters",
                    onActionClick = viewModel::clearFilters
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spaceSmall))

        if (isSeeding && exercises.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.spaceXXLarge),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(Dimens.spaceMedium))
                    Text(
                        text = "Initializing offline exercise database...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SetraTheme.extendedColors.textMuted
                    )
                }
            }
        } else if (exercises.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(Dimens.spaceLarge)
                ) {
                    Text(
                        text = "No exercises found",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(Dimens.spaceSmall))
                    Text(
                        text = "Try adjusting your search query or clearing active filters.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SetraTheme.extendedColors.textMuted
                    )
                    Spacer(modifier = Modifier.height(Dimens.spaceLarge))
                    SetraPrimaryButton(
                        text = "Clear Filters",
                        onClick = viewModel::clearFilters,
                        modifier = Modifier.width(200.dp)
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(Dimens.spaceSmall),
                contentPadding = PaddingValues(bottom = Dimens.spaceXXLarge),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = exercises,
                    key = { it.exercise.id }
                ) { exerciseWithDetails ->
                    ExerciseCard(
                        exerciseWithDetails = exerciseWithDetails,
                        onClick = { onExerciseClick(exerciseWithDetails.exercise.id) }
                    )
                }
            }
        }
    }
}
