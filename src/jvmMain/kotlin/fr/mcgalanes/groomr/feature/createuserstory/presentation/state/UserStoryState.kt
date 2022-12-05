package fr.mcgalanes.groomr.feature.createuserstory.presentation.state

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

data class UserStoryState(
    val needState: StepState.Need = StepState.Need(persona = "", wish = "", goal = ""),
    val kpiState: StepState.Kpi = StepState.Kpi,
    val valueState: StepState.Value = StepState.Value,
    val solutionState: StepState.Solution = StepState.Solution,
    val enablersState: StepState.Enablers = StepState.Enablers,
    val assetsState: StepState.Assets = StepState.Assets,
    val acceptanceCriteriaState: StepState.AcceptanceCriteria = StepState.AcceptanceCriteria,
) {
    operator fun get(step: Step): StepState = when (step) {
        Step.Need -> needState
        Step.Kpi -> kpiState
        Step.Value -> valueState
        Step.Solution -> solutionState
        Step.Enablers -> enablersState
        Step.Assets -> assetsState
        Step.AcceptanceCriteria -> acceptanceCriteriaState
    }
}