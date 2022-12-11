package fr.mcgalanes.groomr.feature.createuserstory.data.repository

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository
import kotlinx.coroutines.flow.MutableStateFlow

class UserStoryRepository : UserStoryRepository {
    companion object {
        private val defaultUserStory =
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
    }

    private val _userStory = MutableStateFlow(defaultUserStory)

    override fun getUserStory(): UserStory = _userStory.value

    override fun saveUserStory(userStory: UserStory) {
        _userStory.value = userStory
    }
}