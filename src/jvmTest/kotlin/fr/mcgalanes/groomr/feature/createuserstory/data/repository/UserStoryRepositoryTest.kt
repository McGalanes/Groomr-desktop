package fr.mcgalanes.groomr.feature.createuserstory.data.repository

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory.Effectivity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
class UserStoryRepositoryTest {
    private lateinit var repository: UserStoryRepository

    @BeforeTest
    fun setUp() {
        repository = UserStoryRepository()
    }

    @Test
    fun `get user story, should return data`() {
        //WHEN
        val actual = repository.getUserStory()

        //THEN
        assertEquals(
            defaultUserStory(),
            actual
        )
    }

    @Test
    fun `save user story, should save data`() {
        //GIVEN
        val userStory = defaultUserStory()
            .copy(
                persona = "Skrillex",
                wish = "release more tracks",
                goal = "make my fans happy",
            )

        //WHEN
        repository.saveUserStory(userStory)

        //THEN
        assertEquals(
            userStory,
            repository.getUserStory(),
        )
    }

    private fun defaultUserStory() =
        UserStory(
            persona = "",
            wish = "",
            goal = "",
            kpis = listOf(),
            businessValue = 0,
            solution = "",
            enablers = emptyList(),
            assets = emptyList(),
            uats = emptyList(),
            effectivity = Effectivity(
                isSmallEnough = false,
                isIndependent = false,
                isEstimable = false,
                isTestable = false
            ),
        )
}