package fr.mcgalanes.groomr.feature.createuserstory.domain

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import kotlin.test.Test
import kotlin.test.assertEquals


internal class GetNextStepUseCaseTest {
    @Test
    fun `should return next step`() {
        //GIVEN
        val useCase = GetNextStepUseCase()

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
                //WHEN
                val actual = useCase(givenStep)

                //THEN
                assertEquals(expectedStep, actual)
            }
    }
}