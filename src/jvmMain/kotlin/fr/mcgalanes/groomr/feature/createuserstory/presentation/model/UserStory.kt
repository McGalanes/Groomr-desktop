package fr.mcgalanes.groomr.feature.createuserstory.presentation.model

data class UserStory(
    val need: Need
) {
    data class Need(
        val persona: String,
        val wish: String,
        val goal: String,
    )
}