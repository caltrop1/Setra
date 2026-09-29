package com.sami.setra.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

enum class ThemeMode {
    SYSTEM,
    LIGHT,
    DARK
}

@Immutable
data class SetraExtendedColors(
    val glassSurface: Color,
    val glassBorder: Color,
    val textMuted: Color
)

val LocalSetraExtendedColors = staticCompositionLocalOf {
    SetraExtendedColors(
        glassSurface = SetraDarkGlassSurface,
        glassBorder = SetraDarkGlassBorder,
        textMuted = SetraDarkTextMuted
    )
}

private val DarkColorScheme = darkColorScheme(
    primary = SetraDarkPrimary,
    onPrimary = SetraDarkOnPrimary,
    primaryContainer = SetraDarkSurfaceVariant,
    onPrimaryContainer = SetraDarkPrimary,
    secondary = SetraDarkSecondary,
    onSecondary = SetraDarkOnSecondary,
    tertiary = SetraDarkTertiary,
    background = SetraDarkBackground,
    onBackground = SetraDarkTextPrimary,
    surface = SetraDarkSurface,
    onSurface = SetraDarkTextPrimary,
    surfaceVariant = SetraDarkSurfaceVariant,
    onSurfaceVariant = SetraDarkTextSecondary,
    outline = SetraDarkOutline,
    outlineVariant = SetraDarkOutlineVariant
)

private val LightColorScheme = lightColorScheme(
    primary = SetraLightPrimary,
    onPrimary = SetraLightOnPrimary,
    primaryContainer = SetraLightSurfaceVariant,
    onPrimaryContainer = SetraLightPrimary,
    secondary = SetraLightSecondary,
    onSecondary = SetraLightOnSecondary,
    tertiary = SetraLightTertiary,
    background = SetraLightBackground,
    onBackground = SetraLightTextPrimary,
    surface = SetraLightSurface,
    onSurface = SetraLightTextPrimary,
    surfaceVariant = SetraLightSurfaceVariant,
    onSurfaceVariant = SetraLightTextSecondary,
    outline = SetraLightOutline,
    outlineVariant = SetraLightOutlineVariant
)

@Composable
fun SetraTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColors = if (darkTheme) {
        SetraExtendedColors(
            glassSurface = SetraDarkGlassSurface,
            glassBorder = SetraDarkGlassBorder,
            textMuted = SetraDarkTextMuted
        )
    } else {
        SetraExtendedColors(
            glassSurface = SetraLightGlassSurface,
            glassBorder = SetraLightGlassBorder,
            textMuted = SetraLightTextMuted
        )
    }

    CompositionLocalProvider(
        LocalSetraExtendedColors provides extendedColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = SetraTypography,
            shapes = SetraShapes,
            content = content
        )
    }
}

object SetraTheme {
    val extendedColors: SetraExtendedColors
        @Composable
        get() = LocalSetraExtendedColors.current
}
