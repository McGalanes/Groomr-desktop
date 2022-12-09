package fr.mcgalanes.groomr.feature.createuserstory.presentation.state

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.UserStory
import kotlin.test.Test
import kotlin.test.assertEquals


internal class userStoryTest {

    private val userStoryState = UserStory()

    @Test
    fun `get by step, should return the right StepState`() {
        //GIVEN
        val params = mapOf(
            Step.Need to userStoryState.needForm,
            Step.Kpi to userStoryState.kpiForm,
            Step.Value to userStoryState.valueForm,
            Step.Solution to userStoryState.solutionForm,
            Step.Enablers to userStoryState.enablersForm,
            Step.Assets to userStoryState.assetsForm,
            Step.UAT to userStoryState.uatForm,
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