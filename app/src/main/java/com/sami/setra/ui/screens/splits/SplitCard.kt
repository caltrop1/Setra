package com.sami.setra.ui.screens.splits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sami.setra.data.model.SplitTemplate
import com.sami.setra.ui.components.SetraCard
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.SetraTheme
import com.sami.setra.ui.theme.getSplitAccentColor

@Composable
fun SplitCard(
    split: SplitTemplate,
    onSplitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val accentColor = getSplitAccentColor(split.id)
    val badgeIcon: ImageVector = when (split.id) {
        "upper_lower" -> Icons.AutoMirrored.Filled.TrendingUp
        "push_pull_legs" -> Icons.Default.FitnessCenter
        "full_body" -> Icons.Default.Person
        "bro_split", "legs_focus" -> Icons.AutoMirrored.Filled.DirectionsRun
        else -> Icons.Default.AutoAwesome
    }

    SetraCard(
        onClick = onSplitClick,
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                role = Role.Button
                contentDescription = "Split template ${split.name}, ${split.targetFrequency}"
            }
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
                // Left Icon Badge
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(Dimens.radiusMedium))
                        .background(accentColor.copy(alpha = 0.18f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = badgeIcon,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(Dimens.iconMedium)
                    )
                }

                Spacer(modifier = Modifier.width(Dimens.spaceMedium))

                Column {
                    Text(
                        text = split.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = split.category,
                        style = MaterialTheme.typography.bodySmall,
                        color = SetraTheme.extendedColors.textMuted
                    )

                    Spacer(modifier = Modifier.height(Dimens.spaceXSmall))

                    // Calendar Frequency Row
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarToday,
                            contentDescription = null,
                            tint = accentColor,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(Dimens.spaceXSmall))
                        Text(
                            text = split.targetFrequency,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = SetraTheme.extendedColors.textMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(Dimens.spaceSmall))

                    // 7-day Circular Day Badges
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val dayLetters = listOf("M", "T", "W", "T", "F", "S", "S")
                        split.days.forEachIndexed { index, day ->
                            val isTrainingDay = !day.isRestDay
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isTrainingDay) accentColor
                                        else MaterialTheme.colorScheme.surface
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = dayLetters.getOrElse(index) { "D" },
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isTrainingDay) Color.White else SetraTheme.extendedColors.textMuted
                                )
                            }
                        }
                    }
                }
            }

            // Chevron Right Arrow
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = SetraTheme.extendedColors.textMuted,
                modifier = Modifier.size(Dimens.iconMedium)
            )
        }
    }
}
