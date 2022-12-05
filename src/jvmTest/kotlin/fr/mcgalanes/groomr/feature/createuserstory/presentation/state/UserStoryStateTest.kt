package fr.mcgalanes.groomr.feature.createuserstory.presentation.state

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import kotlin.test.Test
import kotlin.test.assertEquals


internal class UserStoryStateTest {

    private val userStoryState = UserStoryState()

    @Test
    fun `get by step, should return the right StepState`() {
        //GIVEN
        val params = mapOf(
            Step.Need to userStoryState.needState,
            Step.Kpi to userStoryState.kpiState,
            Step.Value to userStoryState.valueState,
            Step.Solution to userStoryState.solutionState,
            Step.Enablers to userStoryState.enablersState,
            Step.Assets to userStoryState.assetsState,
            Step.AcceptanceCriteria to userStoryState.acceptanceCriteriaState,
        )

        //WHEN
        val expectedToActual =
            params.map { (givenStep, expectedStepState) ->
                expectedStepState to userStoryState[givenStep]
            }

        //THEN
        expectedToActual.forEach { (expected, actual) ->
            assertEquals(expected, actual)
        }
    }
}