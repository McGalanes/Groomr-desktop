package fr.mcgalanes.groomr.feature.createuserstory.domain

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import kotlin.test.Test
import kotlin.test.assertEquals


internal class StepUseCaseTest {

    private val stepUseCase = StepUseCase()

    @Test
    fun `get next step, should return next step or null if current is last`() {
        Step.values()
            .map { givenStep ->
                val expectedNextStep = when (givenStep) {
                    Step.Need -> Step.Kpi
                    Step.Kpi -> Step.Value
                    Step.Value -> Step.Solution
                    Step.Solution -> Step.Enablers
                    Step.Enablers -> Step.Assets
                    Step.Assets -> Step.AcceptanceCriteria
                    Step.AcceptanceCriteria -> null
                }

                givenStep to expectedNextStep
            }
            .forEach { (givenStep, expectedStep) ->
                assertEquals(expectedStep, stepUseCase.getNext(givenStep))
            }
    }

    @Test
    fun `get previous step, should return previous step or null if current is first`() {
        Step.values()
            .map { givenStep ->
                val expectedPreviousStep = when (givenStep) {
                    Step.Need -> null
                    Step.Kpi -> Step.Need
                    Step.Value -> Step.Kpi
                    Step.Solution -> Step.Value
                    Step.Enablers -> Step.Solution
                    Step.Assets -> Step.Enablers
                    Step.AcceptanceCriteria -> Step.Assets
                }

                givenStep to expectedPreviousStep
            }
            .forEach { (givenStep, expectedStep) ->
                assertEquals(expectedStep, stepUseCase.getPrevious(givenStep))
            }
    }
}