package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.test.runTest

class SaveUserStoryUseCaseTest {

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
    fun `should save user story`() = runTest {
        //GIVEN
        val repository = object : UserStoryRepository {
            private val flow = MutableStateFlow<UserStory?>(null)

            override fun getUserStory(): Flow<UserStory> = flow.mapNotNull { it!! }
            override fun saveUserStory(userStory: UserStory) {
                flow.value = userStory
            }
        }

        val saveUserStory = SaveUserStoryUseCase(repository)

        //WHEN
        saveUserStory(userStory)

        //THEN
        assertEquals(userStory, repository.getUserStory().first())
    }
}