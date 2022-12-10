package fr.mcgalanes.groomr.feature.createuserstory.presentation.model

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

sealed class StepForm(val step: Step) {
    data class Need(
        val persona: String,
        val wish: String,
        val goal: String,
    ) : StepForm(Step.Need)

    object Kpi : StepForm(Step.Kpi)
    object Value : StepForm(Step.Value)
    object Solution : StepForm(Step.Solution)
    object Enablers : StepForm(Step.Enablers)
    object Assets : StepForm(Step.Assets)
    object UAT : StepForm(Step.UAT)
}