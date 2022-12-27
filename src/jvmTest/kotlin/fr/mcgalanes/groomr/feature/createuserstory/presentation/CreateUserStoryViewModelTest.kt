package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.SaveStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals


internal class CreateUserStoryViewModelTest {

    private val steps = Step.values()

    private val getSteps: GetStepsUseCase = mockk { every { this@mockk() } returns steps }
    private val getNextStepForm: GetNextStepFormUseCase = mockk()
    private val getPreviousStepForm: GetPreviousStepFormUseCase = mockk()
    private val getStepForm: GetStepFormUseCase = mockk(relaxed = true)
    private val saveStepForm: SaveStepFormUseCase = mockk(relaxed = true)

    private fun viewModel() =
        CreateUserStoryViewModel(
            getSteps,
            getStepForm,
            getNextStepForm,
            getPreviousStepForm,
            saveStepForm,
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
        val nextStepForm = StepForm.Value(0)
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

    @Test
    fun `on persona change, should show and save it`() {
        //GIVEN
        every { getStepForm(any()) } returns StepForm.Need(persona = "", wish = "", goal = "")
        val text = "Sonny Moore"

        val viewModel = viewModel()

        //WHEN
        viewModel.onPersonaChange(text)

        //THEN
        val stepForm = viewModel.uiState.value.stepForm as StepForm.Need
        assertEquals(text, stepForm.persona)
        verify { saveStepForm(stepForm.copy(persona = text)) }
    }

    @Test
    fun `on wish change, should show and save it`() {
        //GIVEN
        every { getStepForm(any()) } returns StepForm.Need(persona = "", wish = "", goal = "")
        val text = "Release more tracks"

        val viewModel = viewModel()

        //WHEN
        viewModel.onWishChange(text)

        //THEN
        val stepForm = viewModel.uiState.value.stepForm as StepForm.Need
        assertEquals(text, stepForm.wish)
        verify { saveStepForm(stepForm.copy(wish = text)) }
    }

    @Test
    fun `on goal change, should show and save it`() {
        //GIVEN
        every { getStepForm(any()) } returns StepForm.Need(persona = "", wish = "", goal = "")
        val text = "Make my fans happy"

        val viewModel = viewModel()

        //WHEN
        viewModel.onGoalChange(text)

        //THEN
        val stepForm = viewModel.uiState.value.stepForm as StepForm.Need
        assertEquals(text, stepForm.goal)
        verify { saveStepForm(stepForm.copy(goal = text)) }
    }

    @Test
    fun `on kpi change, should show and save it`() {
        //GIVEN
        every { getStepForm(any()) } returns StepForm.Kpi(kpi = "")
        val text = "Average basket price should increase"
        val viewModel = viewModel()

        //WHEN
        viewModel.onKpiChange(text)

        //THEN
        val stepForm = viewModel.uiState.value.stepForm as StepForm.Kpi
        assertEquals(text, stepForm.kpi)
        verify { saveStepForm(stepForm.copy(kpi = text)) }
    }

    @Test
    fun `on business value change, should show and save it`() {
        //GIVEN
        every { getStepForm(any()) } returns StepForm.Value(businessValue = 0)
        val text = "75"
        val viewModel = viewModel()

        //WHEN
        viewModel.onBusinessValueChange(text)

        //THEN
        val stepForm = viewModel.uiState.value.stepForm as StepForm.Value
        assertEquals(text.toIntOrNull(), stepForm.businessValue)
        verify { saveStepForm(stepForm.copy(businessValue = text.toIntOrNull()!!)) }
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
