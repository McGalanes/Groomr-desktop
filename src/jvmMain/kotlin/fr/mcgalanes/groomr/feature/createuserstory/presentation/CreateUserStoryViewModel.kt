package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.domain.StepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UiState
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UserStoryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateUserStoryViewModel(
    private val stepUseCase: StepUseCase,
) {
    private val _userStoryState = MutableStateFlow(defaultUserStoryState())
    val userStoryState = _userStoryState.asStateFlow()

    private val _uiState = MutableStateFlow(UiState(stepFormState = userStoryState.value[Step.Need]))
    val uiState = _uiState.asStateFlow()

    fun onNextClick() {
        val currentStep = _uiState.value.stepFormState.step
        stepUseCase.getNext(currentStep)?.let { nextStep ->
            _uiState.update {
                it.copy(stepFormState = userStoryState.value[nextStep])
            }
        }
    }

    fun onPreviousClick() {
        val currentStep = _uiState.value.stepFormState.step
        stepUseCase.getPrevious(currentStep)?.let { previousStep ->
            _uiState.update {
                it.copy(stepFormState = userStoryState.value[previousStep])
            }
        }
    }

    companion object {
        private fun defaultUserStoryState() = UserStoryState()
    }
}