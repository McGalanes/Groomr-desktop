package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.InputType
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.UserStory.Need
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class CreateUserStoryViewModel {

    private val _uiState = MutableStateFlow(
        UiState(
            userStory = UserStory(
                need = Need(
                    persona = "",
                    wish = "",
                    goal = "",
                )
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    fun onInputChange(inputType: InputType, value: String) {
        _uiState.update {
            it.copy(
                userStory = it.userStory.copy(
                    need = it.userStory.need.run {
                        when (inputType) {
                            InputType.PERSONA -> copy(persona = value)
                            InputType.WISH -> copy(wish = value)
                            InputType.GOAL -> copy(goal = value)
                        }
                    }
                )
            )
        }
    }
}