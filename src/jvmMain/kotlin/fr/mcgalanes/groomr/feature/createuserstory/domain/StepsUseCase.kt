package fr.mcgalanes.groomr.feature.createuserstory.domain

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

class StepsUseCase {
    private val steps = Step.values()

    fun getSteps() = steps

    fun getNext(currentStep: Step) =
        steps.find { it.ordinal == currentStep.ordinal + 1 }

    fun getPrevious(currentStep: Step) =
        steps.find { it.ordinal == currentStep.ordinal - 1 }
}