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
import com.sami.setra.ui.components.SplitArtworkImage
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.SetraTheme
import com.sami.setra.ui.theme.SplitArtwork
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

    val imageRes = SplitArtwork.getSplitImageRes(split.id)
    val imageAlignment = SplitArtwork.getSplitImageAlignment(split.id)

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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = Dimens.spaceMedium)
            ) {
                Text(
                    text = split.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = split.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = SetraTheme.extendedColors.textMuted
                )

                Spacer(modifier = Modifier.height(Dimens.spaceSmall))

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
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = SetraTheme.extendedColors.textMuted
                    )
                }

                Spacer(modifier = Modifier.height(Dimens.spaceSmall))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimens.spaceXSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val dayLetters = listOf("M", "T", "W", "T", "F", "S", "S")
                    split.days.forEachIndexed { index, day ->
                        val isTrainingDay = !day.isRestDay
                        Box(
                            modifier = Modifier
                                .size(24.dp)
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

            SplitArtworkImage(
                imageRes = imageRes,
                alignment = imageAlignment,
                modifier = Modifier
                    .width(112.dp)
                    .height(92.dp),
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = Dimens.spaceSmall)
                        .size(Dimens.iconMedium)
                )
            }
        }
    }
}
