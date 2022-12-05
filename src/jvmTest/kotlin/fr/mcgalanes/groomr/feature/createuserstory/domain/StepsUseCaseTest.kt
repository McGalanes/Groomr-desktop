package fr.mcgalanes.groomr.feature.createuserstory.domain

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals


internal class StepsUseCaseTest {

    private val stepsUseCase = StepsUseCase()

    @Test
    fun `get steps, should return all steps`() {
        //WHEN
        val actual = stepsUseCase.getSteps()

        //THEN
        val expected = arrayOf(
            Step.Need,
            Step.Kpi,
            Step.Value,
            Step.Solution,
            Step.Enablers,
            Step.Assets,
            Step.UAT,
        )

        assertContentEquals(
            expected,
            actual
        )
    }

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
                    Step.Assets -> Step.UAT
                    Step.UAT -> null
                }

                givenStep to expectedNextStep
            }
            .forEach { (givenStep, expectedStep) ->
                assertEquals(expectedStep, stepsUseCase.getNext(givenStep))
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
                    Step.UAT -> Step.Assets
                }

                givenStep to expectedPreviousStep
            }
            .forEach { (givenStep, expectedStep) ->
                assertEquals(expectedStep, stepsUseCase.getPrevious(givenStep))
            }
    }
}