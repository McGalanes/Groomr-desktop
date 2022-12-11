package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository
import kotlin.test.Test
import kotlin.test.assertEquals


class GetUserStoryUseCaseTest {

    private val userStory =
        UserStory(
            persona = "driver",
            wish = "to block badly behaved passengers",
            goal = "they are never shown me again",
            kpi = "",
            businessValue = 0,
            solution = "",
            enablers = emptyList(),
            assets = emptyList(),
            uats = emptyList(),
            effectivity = UserStory.Effectivity(
                isSmallEnough = false,
                isIndependent = false,
                isEstimable = false,
                isTestable = false
            ),
        )

    @Test
    fun `should return user story`() {
        //GIVEN
        val repository = object : UserStoryRepository {
            override fun getUserStory(): UserStory = userStory
            override fun saveUserStory(userStory: UserStory) {}
        }

        val getUserStory = GetUserStoryUseCase(repository)

        //WHEN
        val actual = getUserStory()

        //THEN
        assertEquals(userStory, actual)
    }
}