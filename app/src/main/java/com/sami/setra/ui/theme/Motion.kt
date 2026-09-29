package com.sami.setra.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.AnimationSpec

object Motion {
    // Animation Durations (ms)
    const val durationFast = 150
    const val durationNormal = 300
    const val durationSlow = 500

    // Easing curves
    val EasingEmphasized = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
    val EasingStandard = CubicBezierEasing(0.2f, 0.0f, 0.8f, 1.0f)

    // Default Animation Specs
    fun <T> fastSpec(): AnimationSpec<T> = tween(durationFast, easing = EasingEmphasized)
    fun <T> normalSpec(): AnimationSpec<T> = tween(durationNormal, easing = EasingEmphasized)
    fun <T> slowSpec(): AnimationSpec<T> = tween(durationSlow, easing = EasingStandard)
}
