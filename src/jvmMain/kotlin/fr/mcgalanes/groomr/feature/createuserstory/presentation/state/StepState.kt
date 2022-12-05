package fr.mcgalanes.groomr.feature.createuserstory.presentation.state

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

sealed class StepState(val step: Step) {
    data class Need(
        val persona: String,
        val wish: String,
        val goal: String,
    ) : StepState(Step.Need)

    object Kpi : StepState(Step.Kpi)
    object Value : StepState(Step.Value)
    object Solution : StepState(Step.Solution)
    object Enablers : StepState(Step.Enablers)
    object Assets : StepState(Step.Assets)
    object AcceptanceCriteria : StepState(Step.AcceptanceCriteria)
}
