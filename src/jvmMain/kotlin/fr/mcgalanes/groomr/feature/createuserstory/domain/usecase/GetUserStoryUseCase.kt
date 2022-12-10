package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository

class GetUserStoryUseCase(
    private val repository: UserStoryRepository
) {
    operator fun invoke() = repository.getUserStory()
}