package fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.kpi

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

class KpiFormViewModelTest {

    private val getStepForm: GetStepFormUseCase = mockk()
    private val saveStepForm: SaveStepFormUseCase = mockk(relaxed = true)
    private lateinit var viewModel: KpiFormViewModel

    @BeforeTest
    fun setUp() {
        every { getStepForm(Step.Kpi) } returns StepForm.Kpi("")

        viewModel = KpiFormViewModel(getStepForm, saveStepForm)
    }

    @Test
    fun `on init, should show empty form`() {
        //THEN
        assertEquals(
            StepForm.Kpi(""),
            viewModel.formState.value,
        )
    }

    @Test
    fun `on kpi change, should show and save it`() {
        //GIVEN
        val text = "payemnts should increase"

        //WHEN
        viewModel.onKpiChange(text)

        //THEN
        val stepForm = viewModel.formState.value
        assertEquals(text, stepForm.kpi)
        verify { saveStepForm(stepForm.copy(kpi = text)) }
    }
}