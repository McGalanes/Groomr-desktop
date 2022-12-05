package fr.mcgalanes.groomr.feature.createuserstory.presentation.state

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

sealed class StepFormState(val step: Step) {
    data class Need(
        val persona: String,
        val wish: String,
        val goal: String,
    ) : StepFormState(Step.Need)

    object Kpi : StepFormState(Step.Kpi)
    object Value : StepFormState(Step.Value)
    object Solution : StepFormState(Step.Solution)
    object Enablers : StepFormState(Step.Enablers)
    object Assets : StepFormState(Step.Assets)
    object AcceptanceCriteria : StepFormState(Step.AcceptanceCriteria)
}
