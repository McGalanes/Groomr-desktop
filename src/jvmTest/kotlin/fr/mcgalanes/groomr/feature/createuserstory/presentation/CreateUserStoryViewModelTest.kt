package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals


internal class CreateUserStoryViewModelTest {

    private val steps = Step.values()

    private val getSteps: GetStepsUseCase = mockk {
        every { this@mockk() } returns steps
    }
    private val getNextStep: GetNextStepUseCase = mockk()
    private val getPreviousStep: GetPreviousStepUseCase = mockk()

    private fun viewModel() =
        CreateUserStoryViewModel(
            getSteps,
            getNextStep,
            getPreviousStep,
        )

    @Test
    fun `on init, should show Need step`() {
        //WHEN
        val viewModel = viewModel()

        //THEN
        viewModel.assertStepsItems(steps, expectedSelectedStep = Step.Need)
        assertEquals(
            StepForm.Need(persona = "", wish = "", goal = ""),
            viewModel.uiState.value.stepForm,
        )
    }

    @Test
    fun `on next click, should show next step`() {
        //GIVEN
        val nextStep = steps.random()
        every { getNextStep(any()) } returns nextStep

        val viewModel = viewModel()

        //WHEN
        viewModel.onNextClick()

        //THEN
        assertEquals(
            viewModel.userStoryState.value[nextStep],
            viewModel.uiState.value.stepForm,
        )
        viewModel.assertStepsItems(steps, expectedSelectedStep = nextStep)
    }

    @Test
    fun `on previous click, should show previous step`() {
        //GIVEN
        val previousStep = steps.random()
        every { getPreviousStep(any()) } returns previousStep

        val viewModel = viewModel()

        //WHEN
        viewModel.onPreviousClick()

        //THEN
        assertEquals(
            viewModel.userStoryState.value[previousStep],
            viewModel.uiState.value.stepForm,
        )
        viewModel.assertStepsItems(steps, expectedSelectedStep = previousStep)
    }

    @Test
    fun `on nav step click, should show step`() {
        //GIVEN
        val selectedStep = steps.random()
        val viewModel = viewModel()

        //WHEN
        viewModel.onNavStepClick(selectedStep)

        //THEN
        viewModel.assertStepsItems(steps, expectedSelectedStep = selectedStep)
        assertEquals(
            viewModel.userStoryState.value[selectedStep],
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
