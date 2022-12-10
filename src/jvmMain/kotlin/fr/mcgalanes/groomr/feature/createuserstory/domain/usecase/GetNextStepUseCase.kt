package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

class GetNextStepUseCase {
    private val steps = Step.values()

    operator fun invoke(currentStep: Step) =
        steps.find { it.ordinal == currentStep.ordinal + 1 }
}