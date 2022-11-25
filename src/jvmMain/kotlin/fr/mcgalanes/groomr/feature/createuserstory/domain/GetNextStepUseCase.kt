package fr.mcgalanes.groomr.feature.createuserstory.domain

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

class GetNextStepUseCase {

    operator fun invoke(currentStep: Step) =
        when (currentStep) {
            Step.Need -> Step.Kpi
            Step.Kpi -> Step.Value
            Step.Value -> Step.Solution
            Step.Solution -> Step.Enablers
            Step.Enablers -> Step.Assets
            Step.Assets -> Step.AcceptanceCriteria
            Step.AcceptanceCriteria -> null
        }
}