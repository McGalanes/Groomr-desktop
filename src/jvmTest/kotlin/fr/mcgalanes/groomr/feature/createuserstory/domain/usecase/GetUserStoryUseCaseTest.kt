package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest


class GetUserStoryUseCaseTest {

    private val userStory =
        UserStory(
            persona = "",
            wish = "",
            goal = "",
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
    fun `should return user story`() = runTest {
        //GIVEN
        val repository = object : UserStoryRepository {
            override fun getUserStory(): Flow<UserStory> = flowOf(userStory)
            override fun saveUserStory(userStory: UserStory) {}
        }

        val getUserStory = GetUserStoryUseCase(repository)

        //WHEN
        val actual = getUserStory()

        //THEN
        assertEquals(userStory, actual.first())
    }
}