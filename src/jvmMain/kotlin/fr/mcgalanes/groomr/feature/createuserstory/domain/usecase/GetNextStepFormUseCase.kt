package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

class GetNextStepFormUseCase(
    private val getNextStep: GetNextStepUseCase,
    private val getStepForm: GetStepFormUseCase,
) {
    operator fun invoke(currentStep: Step) = getStepForm(getNextStep(currentStep))
}