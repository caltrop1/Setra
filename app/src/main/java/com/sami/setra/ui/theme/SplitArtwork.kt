package com.sami.setra.ui.theme

import androidx.compose.ui.Alignment
import com.sami.setra.R

object SplitArtwork {
    fun getSplitImageRes(splitId: String?): Int {
        return when (resolveSplitImageKey(splitId)) {
            "upper_lower" -> R.drawable.img_split_upper_lower
            "push_pull_legs" -> R.drawable.img_split_ppl
            "bro_split" -> R.drawable.img_split_bro
            "full_body" -> R.drawable.img_split_full_body
            "arnold_split" -> R.drawable.img_split_arnold
            "legs_focus" -> R.drawable.img_split_legs
            "custom" -> R.drawable.img_split_custom
            else -> R.drawable.img_split_custom
        }
    }

    fun getSplitImageAlignment(splitId: String?): Alignment {
        return getImageAlignment(resolveSplitImageKey(splitId))
    }

    fun getRoutineImageRes(routineName: String?, builtInImageId: String? = null): Int {
        return getSplitImageRes(resolveRoutineImageKey(routineName, builtInImageId))
    }

    fun getRoutineImageAlignment(routineName: String?, builtInImageId: String? = null): Alignment {
        return getImageAlignment(resolveRoutineImageKey(routineName, builtInImageId))
    }

    private fun resolveSplitImageKey(splitId: String?): String {
        return when (splitId ?: "custom") {
            "upper_lower" -> "upper_lower"
            "push_pull_legs" -> "push_pull_legs"
            "bro_split" -> "bro_split"
            "full_body" -> "full_body"
            "arnold_split" -> "arnold_split"
            "legs_focus" -> "legs_focus"
            "custom" -> "custom"
            else -> "custom"
        }
    }

    private fun resolveRoutineImageKey(routineName: String?, builtInImageId: String? = null): String {
        val name = routineName.orEmpty().lowercase()
        val id = builtInImageId?.lowercase()

        return when {
            id == "upper_lower" || "upper" in name || "lower" in name -> "upper_lower"
            id == "push_pull_legs" || "push" in name || "pull" in name || "legs" in name -> "push_pull_legs"
            id == "bro_split" || "bro" in name -> "bro_split"
            id == "full_body" || "full body" in name || "full" in name -> "full_body"
            id == "arnold_split" || "arnold" in name -> "arnold_split"
            id == "legs_focus" -> "legs_focus"
            id == "custom" || "custom" in name -> "custom"
            else -> "custom"
        }
    }

    private fun getImageAlignment(imageKey: String): Alignment {
        return when (imageKey) {
            "arnold_split" -> Alignment.TopCenter
            "bro_split" -> Alignment.TopCenter
            "full_body" -> Alignment.Center
            "push_pull_legs" -> Alignment.Center
            "upper_lower" -> Alignment.BottomCenter
            "legs_focus" -> Alignment.BottomCenter
            "custom" -> Alignment.Center
            else -> Alignment.Center
        }
    }
}
