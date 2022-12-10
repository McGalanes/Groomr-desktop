package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateUserStoryViewModel(
    getSteps: GetStepsUseCase,
    private val getNextStep: GetNextStepUseCase,
    private val getPreviousStep: GetPreviousStepUseCase,
) {
    private val _userStoryState = MutableStateFlow(UserStory())
    val userStoryState = _userStoryState.asStateFlow()

    private val _uiState = MutableStateFlow(
        UiState(
            stepsItems = emptyList(),
            stepForm = userStoryState.value[Step.Need],
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        val steps = getSteps()
        val defaultSelectedStep = steps[0]

        _uiState.update { it.copy(stepForm = userStoryState.value[defaultSelectedStep]) }

        _uiState.update {
            it.copy(
                stepsItems = steps.map { step ->
                    step.toStepItem(isSelected = step == defaultSelectedStep)
                }
            )
        }
    }

    fun onNextClick() {
        val currentStep = _uiState.value.stepForm.step
        getNextStep(currentStep)?.let(::selectStep)
    }

    fun onPreviousClick() {
        val currentStep = _uiState.value.stepForm.step
        getPreviousStep(currentStep)?.let(::selectStep)
    }

    fun onNavStepClick(step: Step) = selectStep(step)

    private fun selectStep(step: Step) {
        _uiState.update {
            it.copy(
                stepForm = userStoryState.value[step],
                stepsItems = it.stepsItems.map { item ->
                    item.copy(isSelected = item.step == step)
                }
            )
        }
    }
}
