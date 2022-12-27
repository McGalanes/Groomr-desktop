package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.UserStory
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals


class GetStepFormUseCaseTest {

    @Test
    fun `should return step form according to the step`() {
        //GIVEN
        val userStory = givenUserStory()

        val getUserStory: GetUserStoryUseCase = mockk {
            every { this@mockk() } returns userStory
        }

        val getStepForm = GetStepFormUseCase(getUserStory)

        givenStepToExpectedStepForm(userStory).forEach { (givenStep, expectedStepForm) ->
            //WHEN
            val actual = getStepForm(givenStep)

            //THEN
            assertEquals(expected = expectedStepForm, actual = actual)
        }
    }

    private fun givenStepToExpectedStepForm(userStory: UserStory) = Step.values().map { step ->
        when (step) {
            Step.Need -> step to StepForm.Need(userStory.persona, userStory.wish, userStory.goal)
            Step.Kpi -> step to StepForm.Kpi(userStory.kpi)
            Step.Value -> step to StepForm.Value(userStory.businessValue)
            Step.Solution -> step to StepForm.Solution
            Step.Enablers -> step to StepForm.Enablers
            Step.Assets -> step to StepForm.Assets
            Step.UAT -> step to StepForm.UAT
        }
    }

    private fun givenUserStory() = UserStory(
        persona = "",
        wish = "",
        goal = "",
        kpi = "",
        businessValue = 0,
        solution = "",
        enablers = emptyList(),
        assets = emptyList(),
        uats = emptyList(),
        effectivity = UserStory.Effectivity(
            isSmallEnough = false,
            isIndependent = false,
            isEstimable = false,
            isTestable = false
        ),
    )
}