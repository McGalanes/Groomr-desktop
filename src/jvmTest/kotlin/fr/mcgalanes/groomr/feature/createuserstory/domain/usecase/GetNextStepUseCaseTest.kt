package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import kotlin.test.Test
import kotlin.test.assertEquals

class GetNextStepUseCaseTest {

    @Test
    fun `should return next step or null if current is last`() {
        val getNextStep = GetNextStepUseCase()
        Step.values()
            .map { givenStep ->
                val expectedNextStep = when (givenStep) {
                    Step.Need -> Step.Kpi
                    Step.Kpi -> Step.Value
                    Step.Value -> Step.Solution
                    Step.Solution -> Step.Enablers
                    Step.Enablers -> Step.Assets
                    Step.Assets -> Step.UAT
                    Step.UAT -> givenStep
                }

                givenStep to expectedNextStep
            }
            .forEach { (givenStep, expectedStep) ->
                assertEquals(expectedStep, getNextStep(givenStep))
            }
    }
}