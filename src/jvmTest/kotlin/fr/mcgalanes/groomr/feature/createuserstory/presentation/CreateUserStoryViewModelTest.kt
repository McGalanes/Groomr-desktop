package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.domain.StepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.StepsNavBarState
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


internal class CreateUserStoryViewModelTest {

    private val stepsUseCase: StepsUseCase = mockk {
        every { getSteps() } returns emptyArray()
    }

    private fun viewModel() = CreateUserStoryViewModel(stepsUseCase)

    @Test
    fun `on init, should show steps nav bar with 'need step' selected`() {
        //GIVEN
        val steps = Step.values()
        every { stepsUseCase.getSteps() } returns steps

        //WHEN
        val viewModel = viewModel()

        //THEN
        assertEquals(
            StepsNavBarState(
                items = steps
                    .map {
                        StepsNavBarState.ItemState(
                            item = it.toStepItem(),
                            isSelected = it == Step.Need
                        )
                    }
            ),
            viewModel.uiState.value.stepsNavBarState,
        )
    }

    @Test
    fun `on next click, should show next step`() {
        //GIVEN
        val nextStep = Step.values().random()
        every { stepsUseCase.getNext(any()) } returns nextStep

        val viewModel = viewModel()

        //WHEN
        viewModel.onNextClick()

        //THEN
        assertEquals(
            viewModel.userStoryState.value[nextStep],
            viewModel.uiState.value.stepFormState,
        )
    }

    @Test
    fun `on previous click, should show previous step`() {
        //GIVEN
        val previousStep = Step.values().random()
        every { stepsUseCase.getPrevious(any()) } returns previousStep

        val viewModel = viewModel()

        //WHEN
        viewModel.onPreviousClick()

        //THEN
        assertEquals(
            viewModel.userStoryState.value[previousStep],
            viewModel.uiState.value.stepFormState,
        )
    }

    @Test
    fun `on nav step click, should exclusively select this step`() {
        //GIVEN
        val steps = Step.values()
        every { stepsUseCase.getSteps() } returns steps

        val selectedStep = steps.random()
        val viewModel = viewModel()

        //WHEN
        viewModel.onNavStepClick(selectedStep)

        //THEN
        viewModel
            .uiState
            .value
            .stepsNavBarState
            .items
            .forEach { itemState ->
                when (itemState.item.step) {
                    selectedStep ->
                        assertTrue(
                            actual = itemState.isSelected,
                            message = "Item `${itemState.item.step}` should be selected"
                        )

                    else ->
                        assertFalse(
                            actual = itemState.isSelected,
                            message = "Item '${itemState.item.step}' should not be selected"
                        )
                }
            }
    }
}
