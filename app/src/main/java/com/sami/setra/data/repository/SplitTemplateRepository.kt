package com.sami.setra.data.repository

import com.sami.setra.data.model.SplitTemplate
import com.sami.setra.data.model.SplitTemplateDay

class SplitTemplateRepository {

    val builtInSplits: List<SplitTemplate> = listOf(
        // 1. Upper / Lower Split
        SplitTemplate(
            id = "upper_lower",
            name = "Upper / Lower",
            description = "A balanced 4-day training structure alternating dedicated upper body and lower body sessions with recovery days.",
            targetFrequency = "4 Days / Week",
            category = "Hypertrophy & Strength",
            days = listOf(
                SplitTemplateDay(1, "Monday", false, "Upper Body A", "Focus on Chest, Back, Shoulders & Arms", listOf("Barbell_Bench_Press_-_Medium_Grip", "Bent_Over_Barbell_Row", "Barbell_Shoulder_Press")),
                SplitTemplateDay(2, "Tuesday", false, "Lower Body A", "Focus on Quads, Hamstrings & Calves", listOf("Barbell_Squat", "Barbell_Deadlift", "Leg_Press")),
                SplitTemplateDay(3, "Wednesday", true, "Rest Day", "Active Recovery & Mobility"),
                SplitTemplateDay(4, "Thursday", false, "Upper Body B", "Hypertrophy Focus for Upper Body", listOf("Incline_Dumbbell_Press", "Dumbbell_Alternate_Bicep_Curl", "Triceps_Pushdown")),
                SplitTemplateDay(5, "Friday", false, "Lower Body B", "Hypertrophy Focus for Posterior Chain & Legs", listOf("Romanian_Deadlift", "Leg_Extensions", "Lying_Leg_Curls")),
                SplitTemplateDay(6, "Saturday", true, "Rest Day", "Rest & Recovery"),
                SplitTemplateDay(7, "Sunday", true, "Rest Day", "Rest & Recovery")
            )
        ),

        // 2. Push / Pull / Legs (PPL)
        SplitTemplate(
            id = "push_pull_legs",
            name = "Push / Pull / Legs",
            description = "A popular 6-day or 3-day split organizing movements by mechanics: Push (Chest/Shoulders/Triceps), Pull (Back/Biceps), and Legs.",
            targetFrequency = "6 Days / Week",
            category = "Bodybuilding & Muscle Building",
            days = listOf(
                SplitTemplateDay(1, "Monday", false, "Push A", "Chest, Shoulders & Triceps", listOf("Barbell_Bench_Press_-_Medium_Grip", "Dumbbell_Shoulder_Press", "Triceps_Pushdown")),
                SplitTemplateDay(2, "Tuesday", false, "Pull A", "Back, Rear Delts & Biceps", listOf("Bent_Over_Barbell_Row", "Barbell_Curl", "Dumbbell_Incline_Row")),
                SplitTemplateDay(3, "Wednesday", false, "Legs A", "Quads, Hamstrings & Calves", listOf("Barbell_Squat", "Leg_Press", "Standing_Calf_Raises")),
                SplitTemplateDay(4, "Thursday", false, "Push B", "Upper Chest, Lateral Delts & Arms", listOf("Incline_Dumbbell_Press", "Side_Lateral_Raise", "Dips_-_Chest_Version")),
                SplitTemplateDay(5, "Friday", false, "Pull B", "Lat Width & Bicep Peak", listOf("Wide-Grip_Lat_Pulldown", "Preacher_Curl", "Face_Pull")),
                SplitTemplateDay(6, "Saturday", false, "Legs B", "Hamstring & Glute Focus", listOf("Romanian_Deadlift", "Leg_Extensions", "Seated_Calf_Raise")),
                SplitTemplateDay(7, "Sunday", true, "Rest Day", "Full Rest & Recovery")
            )
        ),

        // 3. Bro Split (Body Part Split)
        SplitTemplate(
            id = "bro_split",
            name = "Bro Split",
            description = "A classic 5-day body-part split focusing on maximum volume for one major muscle group per day.",
            targetFrequency = "5 Days / Week",
            category = "Hypertrophy & Pump",
            days = listOf(
                SplitTemplateDay(1, "Monday", false, "Chest Day", "Complete Chest Isolation & Compound Work", listOf("Barbell_Bench_Press_-_Medium_Grip", "Incline_Dumbbell_Press", "Dumbbell_Flyes")),
                SplitTemplateDay(2, "Tuesday", false, "Back Day", "Thickness & Width for Back", listOf("Barbell_Deadlift", "Bent_Over_Barbell_Row", "Wide-Grip_Lat_Pulldown")),
                SplitTemplateDay(3, "Wednesday", false, "Shoulder Day", "Deltoids & Traps", listOf("Barbell_Shoulder_Press", "Side_Lateral_Raise", "Barbell_Shrug")),
                SplitTemplateDay(4, "Thursday", false, "Arm Day", "Biceps & Triceps Blast", listOf("Barbell_Curl", "Triceps_Pushdown", "Preacher_Curl")),
                SplitTemplateDay(5, "Friday", false, "Leg Day", "Quads, Hamstrings & Calves", listOf("Barbell_Squat", "Leg_Extensions", "Standing_Calf_Raises")),
                SplitTemplateDay(6, "Saturday", true, "Rest Day", "Rest & Recovery"),
                SplitTemplateDay(7, "Sunday", true, "Rest Day", "Rest & Recovery")
            )
        ),

        // 4. Full Body Split
        SplitTemplate(
            id = "full_body",
            name = "Full Body",
            description = "A high-frequency 3-day weekly split hitting major compound movements in every workout session.",
            targetFrequency = "3 Days / Week",
            category = "Strength & General Fitness",
            days = listOf(
                SplitTemplateDay(1, "Monday", false, "Full Body A", "Squat, Bench Press, & Row Focus", listOf("Barbell_Squat", "Barbell_Bench_Press_-_Medium_Grip", "Bent_Over_Barbell_Row")),
                SplitTemplateDay(2, "Tuesday", true, "Rest Day", "Recovery"),
                SplitTemplateDay(3, "Wednesday", false, "Full Body B", "Deadlift, Overhead Press, & Pulldown Focus", listOf("Barbell_Deadlift", "Barbell_Shoulder_Press", "Wide-Grip_Lat_Pulldown")),
                SplitTemplateDay(4, "Thursday", true, "Rest Day", "Recovery"),
                SplitTemplateDay(5, "Friday", false, "Full Body C", "Leg Press, Incline Press, & Arm Focus", listOf("Leg_Press", "Incline_Dumbbell_Press", "Barbell_Curl")),
                SplitTemplateDay(6, "Saturday", true, "Rest Day", "Recovery"),
                SplitTemplateDay(7, "Sunday", true, "Rest Day", "Recovery")
            )
        ),

        // 5. Arnold Split
        SplitTemplate(
            id = "arnold_split",
            name = "Arnold Split",
            description = "The classic antagonist muscle group pairing: Chest/Back, Shoulders/Arms, and Legs.",
            targetFrequency = "6 Days / Week",
            category = "Classic Physique & Volume",
            days = listOf(
                SplitTemplateDay(1, "Monday", false, "Chest & Back A", "Antagonistic Upper Body Push & Pull", listOf("Barbell_Bench_Press_-_Medium_Grip", "Bent_Over_Barbell_Row", "Incline_Dumbbell_Press")),
                SplitTemplateDay(2, "Tuesday", false, "Shoulders & Arms A", "Delts, Biceps & Triceps", listOf("Barbell_Shoulder_Press", "Barbell_Curl", "Triceps_Pushdown")),
                SplitTemplateDay(3, "Wednesday", false, "Legs & Lower Back A", "Quads, Hamstrings & Calves", listOf("Barbell_Squat", "Barbell_Deadlift", "Standing_Calf_Raises")),
                SplitTemplateDay(4, "Thursday", false, "Chest & Back B", "Volume Focus for Chest & Lat Width", listOf("Dumbbell_Flyes", "Wide-Grip_Lat_Pulldown", "Dips_-_Chest_Version")),
                SplitTemplateDay(5, "Friday", false, "Shoulders & Arms B", "Lateral Delts & Arm Peak", listOf("Side_Lateral_Raise", "Preacher_Curl", "Cable_Rope_Overhead_Triceps_Extension")),
                SplitTemplateDay(6, "Saturday", false, "Legs & Lower Back B", "Posterior Chain Focus", listOf("Romanian_Deadlift", "Leg_Extensions", "Lying_Leg_Curls")),
                SplitTemplateDay(7, "Sunday", true, "Rest Day", "Rest & Recovery")
            )
        ),

        // 6. Custom Template
        SplitTemplate(
            id = "custom",
            name = "Custom Split",
            description = "A flexible 7-day template starting point for designing your own personalized training week.",
            targetFrequency = "Custom Days / Week",
            category = "Personalized",
            days = listOf(
                SplitTemplateDay(1, "Monday", false, "Workout Day 1", "Custom Workout Session"),
                SplitTemplateDay(2, "Tuesday", false, "Workout Day 2", "Custom Workout Session"),
                SplitTemplateDay(3, "Wednesday", true, "Rest Day", "Recovery"),
                SplitTemplateDay(4, "Thursday", false, "Workout Day 3", "Custom Workout Session"),
                SplitTemplateDay(5, "Friday", false, "Workout Day 4", "Custom Workout Session"),
                SplitTemplateDay(6, "Saturday", true, "Rest Day", "Recovery"),
                SplitTemplateDay(7, "Sunday", true, "Rest Day", "Recovery")
            )
        )
    )

    fun getSplitById(splitId: String): SplitTemplate? {
        return builtInSplits.find { it.id == splitId }
    }
}
