package com.sami.setra.ui.screens.splits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sami.setra.data.model.SplitTemplate
import com.sami.setra.ui.components.SetraCard
import com.sami.setra.ui.components.SetraGlassSurface
import com.sami.setra.ui.components.SetraPrimaryButton
import com.sami.setra.ui.components.SetraSectionHeader
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.SetraTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplitDetailScreen(
    split: SplitTemplate,
    onBackClick: () -> Unit,
    onUseSplitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = split.name,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Dimens.spaceMedium, vertical = Dimens.spaceMedium)
        ) {
            // Header Glass Banner
            SetraGlassSurface(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.spaceLarge)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = split.category,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                                .padding(horizontal = Dimens.spaceMedium, vertical = Dimens.spaceXSmall)
                        ) {
                            Text(
                                text = split.targetFrequency,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(Dimens.spaceSmall))

                    Text(
                        text = split.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.semantics { heading() }
                    )

                    Spacer(modifier = Modifier.height(Dimens.spaceXSmall))

                    Text(
                        text = split.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = SetraTheme.extendedColors.textMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(Dimens.spaceLarge))

            SetraSectionHeader(title = "7-Day Weekly Structure")

            Spacer(modifier = Modifier.height(Dimens.spaceSmall))

            // 7 Days
            split.days.forEach { day ->
                SetraCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.spaceXSmall)
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    if (day.isRestDay) MaterialTheme.colorScheme.surface
                                    else MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (day.isRestDay) Icons.Default.Bedtime else Icons.Default.FitnessCenter,
                                contentDescription = null,
                                tint = if (day.isRestDay) SetraTheme.extendedColors.textMuted else MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(Dimens.iconMedium)
                            )
                        }

                        Spacer(modifier = Modifier.width(Dimens.spaceMedium))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = day.dayName,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Text(
                                    text = if (day.isRestDay) "Rest Day" else "Workout",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (day.isRestDay) SetraTheme.extendedColors.textMuted else MaterialTheme.colorScheme.primary
                                )
                            }

                            if (!day.isRestDay && !day.workoutName.isNullOrBlank()) {
                                Spacer(modifier = Modifier.height(Dimens.spaceXSmall))
                                Text(
                                    text = day.workoutName,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                if (!day.workoutDescription.isNullOrBlank()) {
                                    Text(
                                        text = day.workoutDescription,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = SetraTheme.extendedColors.textMuted
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimens.spaceLarge))

            SetraPrimaryButton(
                text = "Use Split (Create My Routine)",
                onClick = onUseSplitClick
            )

            Spacer(modifier = Modifier.height(Dimens.spaceXXLarge))
        }
    }
}
