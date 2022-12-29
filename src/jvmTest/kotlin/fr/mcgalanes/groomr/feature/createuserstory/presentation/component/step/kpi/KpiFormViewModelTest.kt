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
        every { getStepForm(Step.Kpi) } returns StepForm.Kpi(kpis = emptyList())

        viewModel = KpiFormViewModel(getStepForm, saveStepForm)
    }

    @Test
    fun `on init, should show empty form`() {
        //THEN
        assertEquals(
            StepForm.Kpi(kpis = emptyList()),
            viewModel.formState.value,
        )
    }

    @Test
    fun `on new kpi click, should add empty kpi`() {
        //WHEN
        viewModel.onNewKpiClick()

        //THEN
        assertEquals(
            listOf(""),
            viewModel.formState.value.kpis
        )
    }

    @Test
    fun `on kpi change, should show and save it`() {
        //GIVEN
        val index = 1
        val text = "New Kpi"

        viewModel.onNewKpiClick()
        viewModel.onNewKpiClick()

        //WHEN
        viewModel.onKpiChange(index, text)

        //THEN
        val stepForm = viewModel.formState.value
        assertEquals(text, stepForm.kpis[1])

        verify { saveStepForm(stepForm.copy(kpis = stepForm.kpis)) }
    }

    @Test
    fun `on delete kpi click, should remove it`() {
        //GIVEN
        viewModel.onNewKpiClick()
        viewModel.onKpiChange(0, "Kpi 0")

        viewModel.onNewKpiClick()
        viewModel.onKpiChange(1, "Kpi 1")

        viewModel.onNewKpiClick()
        viewModel.onKpiChange(2, "Kpi 2")

        //WHEN
        viewModel.onDeleteKpiClick(index = 1)

        //THEN
        assertEquals(
            listOf("Kpi 0", "Kpi 2"),
            viewModel.formState.value.kpis,
        )
    }
}