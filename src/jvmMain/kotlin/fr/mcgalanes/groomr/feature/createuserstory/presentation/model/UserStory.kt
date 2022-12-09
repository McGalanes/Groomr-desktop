package fr.mcgalanes.groomr.feature.createuserstory.presentation.model

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

data class UserStory(
    val needForm: StepForm.Need = StepForm.Need(persona = "", wish = "", goal = ""),
    val kpiForm: StepForm.Kpi = StepForm.Kpi,
    val valueForm: StepForm.Value = StepForm.Value,
    val solutionForm: StepForm.Solution = StepForm.Solution,
    val enablersForm: StepForm.Enablers = StepForm.Enablers,
    val assetsForm: StepForm.Assets = StepForm.Assets,
    val uatForm: StepForm.UAT = StepForm.UAT,
) {
    operator fun get(step: Step): StepForm = when (step) {
        Step.Need -> needForm
        Step.Kpi -> kpiForm
        Step.Value -> valueForm
        Step.Solution -> solutionForm
        Step.Enablers -> enablersForm
        Step.Assets -> assetsForm
        Step.UAT -> uatForm
    }
}