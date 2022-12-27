package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.SaveStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateUserStoryViewModel(
    getSteps: GetStepsUseCase,
    private val getStepForm: GetStepFormUseCase,
    private val getNextStepForm: GetNextStepFormUseCase,
    private val getPreviousStepForm: GetPreviousStepFormUseCase,
    private val saveStepForm: SaveStepFormUseCase,
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
        showStep(getNextStepForm(currentStep))
    }

    fun onPreviousClick() {
        val currentStep = _uiState.value.stepForm.step
        showStep(getPreviousStepForm(currentStep))
    }

    fun onNavStepClick(step: Step) = showStep(getStepForm(step))

    fun onPersonaChange(text: String) = onFormFieldChange<StepForm.Need> { it.copy(persona = text) }

    fun onWishChange(text: String) = onFormFieldChange<StepForm.Need> { it.copy(wish = text) }

    fun onGoalChange(text: String) = onFormFieldChange<StepForm.Need> { it.copy(goal = text) }

    fun onKpiChange(text: String) = onFormFieldChange<StepForm.Kpi> { it.copy(kpi = text) }

    private inline fun <reified T : StepForm> onFormFieldChange(update: (T) -> T) {
        val stepForm = _uiState.value.stepForm
        if (stepForm !is T) return

        val updatedStepForm = update(stepForm)

        saveStepForm(updatedStepForm)

        _uiState.update {
            it.copy(stepForm = updatedStepForm)
        }
    }

    private fun showStep(stepForm: StepForm) {
        _uiState.update {
            it.copy(
                stepForm = stepForm,
                stepsItems = it.stepsItems.map { item ->
                    item.copy(isSelected = item.step == stepForm.step)
                }
            )
        }
    }
}
