package com.sami.setra.ui.screens.splits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sami.setra.data.model.SplitTemplate
import com.sami.setra.ui.components.SetraCard
import com.sami.setra.ui.components.SetraPrimaryButton
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.SetraTheme

@Composable
fun SplitCard(
    split: SplitTemplate,
    onSplitClick: () -> Unit,
    onUseSplitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SetraCard(
        onClick = onSplitClick,
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                role = Role.Button
                contentDescription = "Split template ${split.name}, ${split.targetFrequency}"
            }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header Row: Title & Frequency Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = split.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = split.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

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
                text = split.description,
                style = MaterialTheme.typography.bodyMedium,
                color = SetraTheme.extendedColors.textMuted,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(Dimens.spaceMedium))

            // 7-day compact weekly preview grid/list
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(Dimens.spaceSmall)
            ) {
                split.days.forEach { day ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = day.dayName.take(3).uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (day.isRestDay) SetraTheme.extendedColors.textMuted else MaterialTheme.colorScheme.primary,
                            modifier = Modifier.width(40.dp)
                        )

                        Text(
                            text = if (day.isRestDay) "Rest Day" else (day.workoutName ?: "Workout"),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = if (day.isRestDay) FontWeight.Normal else FontWeight.Medium,
                            color = if (day.isRestDay) SetraTheme.extendedColors.textMuted else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimens.spaceMedium))

            SetraPrimaryButton(
                text = "Use Split",
                onClick = onUseSplitClick
            )
        }
    }
}
