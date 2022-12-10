package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import kotlin.test.Test
import kotlin.test.assertEquals

class GetPreviousStepUseCaseTest {

    @Test
    fun `should return previous step or null if current is first`() {
        val getPreviousStep = GetPreviousStepUseCase()

        Step.values()
            .map { givenStep ->
                val expectedPreviousStep = when (givenStep) {
                    Step.Need -> null
                    Step.Kpi -> Step.Need
                    Step.Value -> Step.Kpi
                    Step.Solution -> Step.Value
                    Step.Enablers -> Step.Solution
                    Step.Assets -> Step.Enablers
                    Step.UAT -> Step.Assets
                }

                givenStep to expectedPreviousStep
            }
            .forEach { (givenStep, expectedStep) ->
                assertEquals(expectedStep, getPreviousStep(givenStep))
            }
    }
}