package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateUserStoryViewModel(
    getSteps: GetStepsUseCase,
    private val getNextStep: GetNextStepUseCase,
    private val getPreviousStep: GetPreviousStepUseCase,
    private val getStepForm: GetStepFormUseCase,
) {

    private val defaultSelectedStep = Step.Need

    private val _uiState = MutableStateFlow(
        UiState(
            stepsItems = getSteps().map { it.toStepItem(isSelected = it == defaultSelectedStep) },
            stepForm = getStepForm(defaultSelectedStep),
        )
    )
    val uiState = _uiState.asStateFlow()


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
                stepForm = getStepForm(step),
                stepsItems = it.stepsItems.map { item ->
                    item.copy(isSelected = item.step == step)
                }
            )
        }
    }
}
