package fr.mcgalanes.groomr.feature.createuserstory.domain.usecase

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertEquals
import org.junit.Test

class GetPreviousStepFormUseCaseTest {

    @Test
    fun `should return previous step form`() {
        //GIVEN
        val currentStep = Step.values().random()
        val previousStep = Step.values().random()

        val getPreviousStep: GetPreviousStepUseCase = mockk {
            every { this@mockk(currentStep) } returns previousStep
        }

        val previousStepForm = StepForm.Value(0)
        val getStepForm: GetStepFormUseCase = mockk {
            every { this@mockk(previousStep) } returns previousStepForm
        }

        val getPreviousStepForm = GetPreviousStepFormUseCase(getPreviousStep, getStepForm)

        //WHEN
        val actual = getPreviousStepForm(currentStep)

        //THEN
        assertEquals(previousStepForm, actual)
    }
}