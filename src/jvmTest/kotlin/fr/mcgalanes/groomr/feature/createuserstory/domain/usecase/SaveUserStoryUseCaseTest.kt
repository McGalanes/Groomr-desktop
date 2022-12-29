package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class SaveUserStoryUseCaseTest {

    private val userStory =
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
            effectivity = UserStory.Effectivity(
                isSmallEnough = false,
                isIndependent = false,
                isEstimable = false,
                isTestable = false
            ),
        )

    @Test
    fun `should save user story`() = runTest {
        //GIVEN
        val repository = object : UserStoryRepository {
            private var userStory: UserStory? = null

            override fun getUserStory(): UserStory = userStory!!
            override fun saveUserStory(userStory: UserStory) {
                this.userStory = userStory
            }
        }

        val saveUserStory = SaveUserStoryUseCase(repository)

        //WHEN
        saveUserStory(userStory)

        //THEN
        assertEquals(userStory, repository.getUserStory())
    }
}