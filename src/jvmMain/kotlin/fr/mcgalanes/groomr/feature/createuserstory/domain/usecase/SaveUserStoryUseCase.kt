package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository

class SaveUserStoryUseCase(
    private val repository: UserStoryRepository
) {
    operator fun invoke(userStory: UserStory) = repository.saveUserStory(userStory)
}