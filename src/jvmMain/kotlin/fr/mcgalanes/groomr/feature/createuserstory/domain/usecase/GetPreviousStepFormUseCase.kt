package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

class GetPreviousStepFormUseCase(
    private val getPreviousStepForm: GetPreviousStepUseCase,
    private val getStepForm: GetStepFormUseCase,
) {
    operator fun invoke(currentStep: Step) = getStepForm(getPreviousStepForm(currentStep))
}