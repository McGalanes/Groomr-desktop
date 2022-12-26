package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository

class SaveStepFormUseCase(private val userStoryRepository: UserStoryRepository) {
    operator fun invoke(stepForm: StepForm) {
        with(userStoryRepository) {
            saveUserStory(
                when (stepForm) {
                    is StepForm.Need ->
                        getUserStory().copy(
                            persona = stepForm.persona,
                            wish = stepForm.wish,
                            goal = stepForm.goal,
                        )

                    else -> TODO()
                }
            )
        }
    }
}