package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

class GetStepsUseCase {
    operator fun invoke() = Step.values()
}