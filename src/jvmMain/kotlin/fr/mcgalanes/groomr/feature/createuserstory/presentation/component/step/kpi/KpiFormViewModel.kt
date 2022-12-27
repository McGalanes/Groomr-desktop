package fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.kpi

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.SaveStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.StepFormViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KpiFormViewModel(
    getStepForm: GetStepFormUseCase,
    private val saveStepForm: SaveStepFormUseCase
) : StepFormViewModel {

    private val _formState = MutableStateFlow(StepForm.Kpi(""))
    val formState = _formState.asStateFlow()

    override val step: Step = Step.Kpi

    init {
        _formState.update { getStepForm(Step.Kpi) as StepForm.Kpi }
    }

    fun onKpiChange(text: String) {
        val updatedStepForm = StepForm.Kpi(text)
        saveStepForm(updatedStepForm)
        _formState.update { updatedStepForm }
    }
}