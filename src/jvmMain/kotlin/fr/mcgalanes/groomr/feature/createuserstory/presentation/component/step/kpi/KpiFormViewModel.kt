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
    private val saveStepForm: SaveStepFormUseCase,
) : StepFormViewModel {

    private val _formState = MutableStateFlow(StepForm.Kpi(listOf()))
    val formState = _formState.asStateFlow()

    override val step: Step = Step.Kpi

    init {
        _formState.update { getStepForm(Step.Kpi) as StepForm.Kpi }
    }

    fun onKpiChange(index: Int, text: String) = _formState.updateAndSaveKpis { it[index] = text }

    fun onNewKpiClick() = _formState.updateAndSaveKpis { it.add("") }

    fun onDeleteKpiClick(index: Int) = _formState.updateAndSaveKpis { it.removeAt(index) }

    private fun MutableStateFlow<StepForm.Kpi>.updateAndSaveKpis(
        update: (MutableList<String>) -> Unit,
    ) = update {
        val kpis = it.kpis.toMutableList()
        update(kpis)
        it.copy(kpis = kpis).also(saveStepForm::invoke)
    }
}