package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertEquals
import org.junit.Test

class GetNextStepFormUseCaseTest {

    @Test
    fun `should return next step form`() {
        //GIVEN
        val currentStep = Step.values().random()
        val nextStep = Step.values().random()

        val getNextStep: GetNextStepUseCase = mockk {
            every { this@mockk(currentStep) } returns nextStep
        }

        val nextStepForm = StepForm.Value
        val getStepForm: GetStepFormUseCase = mockk {
            every { this@mockk(nextStep) } returns nextStepForm
        }

        val getNextStepForm = GetNextStepFormUseCase(getNextStep, getStepForm)

        //WHEN
        val actual = getNextStepForm(currentStep)

        //THEN
        assertEquals(nextStepForm, actual)
    }
}