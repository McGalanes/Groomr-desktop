package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals


internal class CreateUserStoryViewModelTest {

    private val steps = Step.values()

    private val getSteps: GetStepsUseCase = mockk { every { this@mockk() } returns steps }
    private val getNextStep: GetNextStepUseCase = mockk()
    private val getPreviousStep: GetPreviousStepUseCase = mockk()

    private fun viewModel() =
        CreateUserStoryViewModel(
            getSteps,
            getPreviousStep,
            getNextStep,
        )

    @Test
    fun `on init, should show Need step`() {
        //WHEN
        val viewModel = viewModel()

        //THEN
        viewModel.assertStepsItems(steps, expectedSelectedStep = Step.Need)
    }

    @Test
    fun `on next click, should show next step`() {
        //GIVEN
        val nextStep = Step.values().random()
        every { getNextStep(any()) } returns nextStep

        val viewModel = viewModel()

        //WHEN
        viewModel.onNextClick()

        //THEN
        assertEquals(
            nextStep,
            viewModel.uiState.value.step,
        )
        viewModel.assertStepsItems(steps, expectedSelectedStep = nextStep)
    }

    @Test
    fun `on previous click, should show previous step`() {
        //GIVEN
        val previousStep = Step.values().random()
        every { getPreviousStep(any()) } returns previousStep

        val viewModel = viewModel()

        //WHEN
        viewModel.onPreviousClick()

        //THEN
        assertEquals(
            previousStep,
            viewModel.uiState.value.step,
        )
        viewModel.assertStepsItems(steps, expectedSelectedStep = previousStep)
    }

    @Test
    fun `on nav step click, should show step`() {
        //GIVEN
        val selectedStep = Step.values().random()

        val viewModel = viewModel()

        //WHEN
        viewModel.onNavStepClick(selectedStep)

        //THEN
        viewModel.assertStepsItems(steps, expectedSelectedStep = selectedStep)
        assertEquals(
            selectedStep,
            viewModel.uiState.value.step,
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
