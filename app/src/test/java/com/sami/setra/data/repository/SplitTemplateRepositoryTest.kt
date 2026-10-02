package com.sami.setra.data.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SplitTemplateRepositoryTest {

    private lateinit var repository: SplitTemplateRepository

    @Before
    fun setUp() {
        repository = SplitTemplateRepository()
    }

    @Test
    fun builtInSplits_containsAllRequiredTemplates() {
        val splits = repository.builtInSplits
        assertEquals(6, splits.size)

        val splitIds = splits.map { it.id }.toSet()
        assertTrue(splitIds.contains("upper_lower"))
        assertTrue(splitIds.contains("push_pull_legs"))
        assertTrue(splitIds.contains("bro_split"))
        assertTrue(splitIds.contains("full_body"))
        assertTrue(splitIds.contains("arnold_split"))
        assertTrue(splitIds.contains("custom"))
    }

    @Test
    fun splitTemplates_eachContainSevenDays() {
        repository.builtInSplits.forEach { split ->
            assertEquals("Split ${split.name} must have 7 days", 7, split.days.size)
            val dayNumbers = split.days.map { it.dayOfWeek }
            assertEquals((1..7).toList(), dayNumbers)
        }
    }

    @Test
    fun upperLowerSplit_hasCorrectActiveAndRestDays() {
        val upperLower = repository.getSplitById("upper_lower")
        assertNotNull(upperLower)
        assertEquals(4, upperLower!!.activeDaysCount)
        assertEquals(3, upperLower.restDaysCount)
    }
}
