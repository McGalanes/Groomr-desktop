package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm

class GetStepFormUseCase(
    private val getUserStory: GetUserStoryUseCase,
) {
    operator fun invoke(step: Step): StepForm {
        val userStory = getUserStory()

        return when (step) {
            Step.Need -> StepForm.Need(
                persona = userStory.persona,
                wish = userStory.wish,
                goal = userStory.goal
            )

            Step.Kpi -> StepForm.Kpi
            Step.Value -> StepForm.Value
            Step.Solution -> StepForm.Solution
            Step.Enablers -> StepForm.Enablers
            Step.Assets -> StepForm.Assets
            Step.UAT -> StepForm.UAT
        }
    }
}