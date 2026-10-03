package com.sami.setra.ui.theme

import androidx.compose.ui.graphics.Color

// Dark Theme Colors (Very dark green / near-black with green accents)
val SetraDarkBackground = Color(0xFF0A0F0D)
val SetraDarkSurface = Color(0xFF121915)
val SetraDarkSurfaceVariant = Color(0xFF1A231C)
val SetraDarkPrimary = Color(0xFF22C55E) // Vibrant Gym Green
val SetraDarkOnPrimary = Color(0xFF021607)
val SetraDarkSecondary = Color(0xFF34D399) // Mint/Teal Accent
val SetraDarkOnSecondary = Color(0xFF021607)
val SetraDarkTertiary = Color(0xFF4ADE80)
val SetraDarkTextPrimary = Color(0xFFF8FAFC)
val SetraDarkTextSecondary = Color(0xFF94A3B8)
val SetraDarkTextMuted = Color(0xFF64748B)
val SetraDarkOutline = Color(0xFF27382E)
val SetraDarkOutlineVariant = Color(0xFF1D2C22)

// Dark Glass Colors
val SetraDarkGlassSurface = Color(0xD9121915)
val SetraDarkGlassBorder = Color(0x2622C55E)

// Light Theme Colors (Clean white/light neutral with blue accents)
val SetraLightBackground = Color(0xFFF4F7FB)
val SetraLightSurface = Color(0xFFFFFFFF)
val SetraLightSurfaceVariant = Color(0xFFE2E8F0)
val SetraLightPrimary = Color(0xFF2563EB) // Modern Blue
val SetraLightOnPrimary = Color(0xFFFFFFFF)
val SetraLightSecondary = Color(0xFF0284C7) // Sky Blue Accent
val SetraLightOnSecondary = Color(0xFFFFFFFF)
val SetraLightTertiary = Color(0xFF3B82F6)
val SetraLightTextPrimary = Color(0xFF0F172A)
val SetraLightTextSecondary = Color(0xFF64748B)
val SetraLightTextMuted = Color(0xFF94A3B8)
val SetraLightOutline = Color(0xFFCBD5E1)
val SetraLightOutlineVariant = Color(0xFFE2E8F0)

// Light Glass Colors
val SetraLightGlassSurface = Color(0xE6FFFFFF)
val SetraLightGlassBorder = Color(0x1F2563EB)

// Split Category Accent Colors
val SplitGreenAccent = Color(0xFF22C55E)
val SplitBlueAccent = Color(0xFF3B82F6)
val SplitPurpleAccent = Color(0xFFA855F7)
val SplitOrangeAccent = Color(0xFFF97316)
val SplitTealAccent = Color(0xFF14B8A6)

fun getSplitAccentColor(splitId: String): Color {
    return when (splitId) {
        "upper_lower" -> SplitGreenAccent
        "push_pull_legs" -> SplitBlueAccent
        "full_body" -> SplitPurpleAccent
        "bro_split", "legs_focus" -> SplitOrangeAccent
        "arnold_split" -> SplitGreenAccent
        else -> SplitTealAccent
    }
}
