package fr.mcgalanes.groomr.feature.createuserstory.domain.model

sealed class StepForm(val step: Step) {
    data class Need(
        val persona: String,
        val wish: String,
        val goal: String,
    ) : StepForm(Step.Need)

    data class Kpi(val kpi: String) : StepForm(Step.Kpi)
    object Value : StepForm(Step.Value)
    object Solution : StepForm(Step.Solution)
    object Enablers : StepForm(Step.Enablers)
    object Assets : StepForm(Step.Assets)
    object UAT : StepForm(Step.UAT)
}
