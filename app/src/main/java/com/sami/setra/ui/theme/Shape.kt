package com.sami.setra.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes

val SetraShapes = Shapes(
    small = RoundedCornerShape(Dimens.radiusSmall),
    medium = RoundedCornerShape(Dimens.radiusMedium),
    large = RoundedCornerShape(Dimens.radiusLarge),
    extraLarge = RoundedCornerShape(Dimens.radiusXLarge)
)

val CardShape = RoundedCornerShape(Dimens.radiusMedium)
val ButtonShape = RoundedCornerShape(Dimens.radiusMedium)
val GlassSurfaceShape = RoundedCornerShape(Dimens.radiusLarge)
val BottomNavShape = RoundedCornerShape(topStart = Dimens.radiusLarge, topEnd = Dimens.radiusLarge)
