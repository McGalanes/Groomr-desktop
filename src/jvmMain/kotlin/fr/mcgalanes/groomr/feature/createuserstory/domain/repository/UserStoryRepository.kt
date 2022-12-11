package fr.mcgalanes.groomr.feature.createuserstory.domain.repository

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory

interface UserStoryRepository {
    fun getUserStory(): UserStory
    fun saveUserStory(userStory: UserStory)
}