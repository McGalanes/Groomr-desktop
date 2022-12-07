package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.domain.StepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.StepsNavBarState
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UiState
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UserStoryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateUserStoryViewModel(
    private val stepsUseCase: StepsUseCase,
) {
    private val _userStoryState = MutableStateFlow(defaultUserStoryState())
    val userStoryState = _userStoryState.asStateFlow()

    private val _uiState = MutableStateFlow(defaultUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val navBarState =
            StepsNavBarState(
                items = stepsUseCase.getSteps()
                    .map {
                        StepsNavBarState.ItemState(
                            item = it.toStepItem(),
                            isSelected = it == Step.Need,
                        )
                    }
            )

        _uiState.update { it.copy(stepsNavBarState = navBarState) }
    }

    fun onNextClick() {
        val currentStep = _uiState.value.stepFormState.step
        stepsUseCase.getNext(currentStep)?.let { nextStep ->
            _uiState.update {
                it.copy(stepFormState = userStoryState.value[nextStep])
            }
        }
    }

    fun onPreviousClick() {
        val currentStep = _uiState.value.stepFormState.step
        stepsUseCase.getPrevious(currentStep)?.let { previousStep ->
            _uiState.update {
                it.copy(stepFormState = userStoryState.value[previousStep])
            }
        }
    }

    private fun defaultUserStoryState() = UserStoryState()
    private fun defaultUiState() =
        UiState(
            stepsNavBarState = StepsNavBarState(items = emptyList()),
            stepFormState = userStoryState.value[Step.Need],
        )

    fun onNavStepClick(step: Step) {
        _uiState.update {
            val navBarState = it.stepsNavBarState
            it.copy(
                stepsNavBarState = navBarState.copy(
                    items = navBarState.items.map { itemState ->
                        itemState.copy(isSelected = itemState.item.step == step)
                    }
                )
            )
        }
    }
}