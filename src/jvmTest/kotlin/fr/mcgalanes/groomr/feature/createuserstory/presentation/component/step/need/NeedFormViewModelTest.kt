package fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.need

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.SaveStepFormUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NeedFormViewModelTest {

    private val getStepForm: GetStepFormUseCase = mockk()
    private val saveStepForm: SaveStepFormUseCase = mockk(relaxed = true)
    private lateinit var viewModel: NeedFormViewModel

    @BeforeTest
    fun setUp() {
        every { getStepForm(Step.Need) } returns StepForm.Need(persona = "", wish = "", goal = "")

        viewModel = NeedFormViewModel(getStepForm, saveStepForm)
    }

    @Test
    fun `on init, should show empty form`() {
        //THEN
        assertEquals(
            StepForm.Need(persona = "", wish = "", goal = ""),
            viewModel.formState.value,
        )
    }

    @Test
    fun `on persona change, should show and save it`() {
        //GIVEN
        val text = "Sonny Moore"

        //WHEN
        viewModel.onPersonaChange(text)

        //THEN
        val stepForm = viewModel.formState.value
        assertEquals(text, stepForm.persona)
        verify { saveStepForm(stepForm.copy(persona = text)) }
    }

    @Test
    fun `on wish change, should show and save it`() {
        //GIVEN
        val text = "Release more tracks"

        //WHEN
        viewModel.onWishChange(text)

        //THEN
        val stepForm = viewModel.formState.value
        assertEquals(text, stepForm.wish)
        verify { saveStepForm(stepForm.copy(wish = text)) }
    }

    @Test
    fun `on goal change, should show and save it`() {
        //GIVEN
        val text = "Make my fans happy"

        //WHEN
        viewModel.onGoalChange(text)

        //THEN
        val stepForm = viewModel.formState.value
        assertEquals(text, stepForm.goal)
        verify { saveStepForm(stepForm.copy(goal = text)) }
    }
}