package com.sami.setra.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.sami.setra.ui.theme.Dimens
import com.sami.setra.ui.theme.GlassSurfaceShape
import com.sami.setra.ui.theme.SetraTheme

@Composable
fun SetraGlassSurface(
    modifier: Modifier = Modifier,
    shape: Shape = GlassSurfaceShape,
    backgroundColor: Color = SetraTheme.extendedColors.glassSurface,
    borderColor: Color = SetraTheme.extendedColors.glassBorder,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .border(width = Dimens.strokeWidthThin, color = borderColor, shape = shape),
        content = content
    )
}
