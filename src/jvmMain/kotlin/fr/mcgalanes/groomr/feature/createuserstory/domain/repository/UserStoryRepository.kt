package fr.mcgalanes.groomr.feature.createuserstory.domain.repository

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory
import kotlinx.coroutines.flow.Flow

interface UserStoryRepository {
    fun getUserStory(): Flow<UserStory>
    fun saveUserStory(userStory: UserStory)
}