package fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.need

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.SaveStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.StepFormViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NeedFormViewModel(
    getStepForm: GetStepFormUseCase,
    private val saveStepForm: SaveStepFormUseCase
) : StepFormViewModel {

    private val _formState = MutableStateFlow(StepForm.Need("", "", ""))
    val formState = _formState.asStateFlow()

    override val step: Step = Step.Need

    init {
        _formState.update { getStepForm(Step.Need) as StepForm.Need }
    }

    fun onPersonaChange(text: String) = onFieldChange { it.copy(persona = text) }

    fun onWishChange(text: String) = onFieldChange { it.copy(wish = text) }

    fun onGoalChange(text: String) = onFieldChange { it.copy(goal = text) }

    private fun onFieldChange(update: (StepForm.Need) -> StepForm.Need) {
        val updatedStepForm = update(_formState.value)
        saveStepForm(updatedStepForm)
        _formState.update { updatedStepForm }
    }
}