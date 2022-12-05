package fr.mcgalanes.groomr.feature.createuserstory.presentation.state

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

data class UserStoryState(
    val needState: StepFormState.Need = StepFormState.Need(persona = "", wish = "", goal = ""),
    val kpiState: StepFormState.Kpi = StepFormState.Kpi,
    val valueState: StepFormState.Value = StepFormState.Value,
    val solutionState: StepFormState.Solution = StepFormState.Solution,
    val enablersState: StepFormState.Enablers = StepFormState.Enablers,
    val assetsState: StepFormState.Assets = StepFormState.Assets,
    val acceptanceCriteriaState: StepFormState.AcceptanceCriteria = StepFormState.AcceptanceCriteria,
) {
    operator fun get(step: Step): StepFormState = when (step) {
        Step.Need -> needState
        Step.Kpi -> kpiState
        Step.Value -> valueState
        Step.Solution -> solutionState
        Step.Enablers -> enablersState
        Step.Assets -> assetsState
        Step.AcceptanceCriteria -> acceptanceCriteriaState
    }
}