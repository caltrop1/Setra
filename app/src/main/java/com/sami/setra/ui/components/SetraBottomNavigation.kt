package com.sami.setra.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import com.sami.setra.navigation.BottomNavItem
import com.sami.setra.navigation.Screen
import com.sami.setra.ui.theme.BottomNavShape
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.Motion

@Composable
fun SetraBottomNavigation(
    currentDestination: Screen,
    items: List<BottomNavItem>,
    onNavigateToDestination: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    SetraGlassSurface(
        shape = BottomNavShape,
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.bottomNavHeight)
                .padding(horizontal = Dimens.spaceSmall),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = currentDestination::class == item.route::class
                val tintColor by animateColorAsState(
                    targetValue = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    animationSpec = Motion.fastSpec(),
                    label = "BottomNavTint"
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(Dimens.minTouchTargetSize)
                        .semantics {
                            role = Role.Tab
                            selected = isSelected
                            contentDescription = item.contentDescription
                        }
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onNavigateToDestination(item.route) }
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = null,
                        tint = tintColor,
                        modifier = Modifier.size(Dimens.iconMedium)
                    )
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = tintColor,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
