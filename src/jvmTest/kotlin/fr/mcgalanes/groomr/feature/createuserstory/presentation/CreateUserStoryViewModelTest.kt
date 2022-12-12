package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals


internal class CreateUserStoryViewModelTest {

    private val steps = Step.values()

    private val getSteps: GetStepsUseCase = mockk { every { this@mockk() } returns steps }
    private val getNextStepForm: GetNextStepFormUseCase = mockk()
    private val getPreviousStepForm: GetPreviousStepFormUseCase = mockk()
    private val getStepForm: GetStepFormUseCase = mockk(relaxed = true)

    private fun viewModel() =
        CreateUserStoryViewModel(
            getSteps,
            getStepForm,
            getNextStepForm,
            getPreviousStepForm,
        )

    @Test
    fun `on init, should show Need step`() {
        //WHEN
        val stepForm = StepForm.Need(persona = "", wish = "", goal = "")
        every { getStepForm(any()) } returns stepForm
        val viewModel = viewModel()

        //THEN
        viewModel.assertStepsItems(steps, expectedSelectedStep = Step.Need)
        assertEquals(
            stepForm,
            viewModel.uiState.value.stepForm,
        )
    }

    @Test
    fun `on next click, should show next step`() {
        //GIVEN
        val nextStepForm = StepForm.Value
        every { getNextStepForm(any()) } returns nextStepForm

        val viewModel = viewModel()

        //WHEN
        viewModel.onNextClick()

        //THEN
        assertEquals(
            nextStepForm,
            viewModel.uiState.value.stepForm,
        )
        viewModel.assertStepsItems(steps, expectedSelectedStep = nextStepForm.step)
    }

    @Test
    fun `on previous click, should show previous step`() {
        //GIVEN
        val previousStepForm = StepForm.Assets
        every { getPreviousStepForm(any()) } returns previousStepForm

        val viewModel = viewModel()

        //WHEN
        viewModel.onPreviousClick()

        //THEN
        assertEquals(
            previousStepForm,
            viewModel.uiState.value.stepForm,
        )
        viewModel.assertStepsItems(steps, expectedSelectedStep = previousStepForm.step)
    }

    @Test
    fun `on nav step click, should show step`() {
        //GIVEN
        val selectedStepForm = StepForm.Solution
        val selectedStep = selectedStepForm.step
        every { getStepForm(any()) } returns selectedStepForm

        val viewModel = viewModel()

        //WHEN
        viewModel.onNavStepClick(selectedStep)

        //THEN
        viewModel.assertStepsItems(steps, expectedSelectedStep = selectedStep)
        assertEquals(
            selectedStepForm,
            viewModel.uiState.value.stepForm,
        )
    }

    private fun CreateUserStoryViewModel.assertStepsItems(
        steps: Array<Step>,
        expectedSelectedStep: Step,
    ) {
        assertEquals(
            steps.map { it.toStepItem(isSelected = it == expectedSelectedStep) },
            uiState.value.stepsItems,
        )
    }
}
