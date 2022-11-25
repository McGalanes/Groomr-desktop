package fr.mcgalanes.groomr.feature.createuserstory.presentation

import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.InputType
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.UserStory
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.UserStory.Need
import kotlin.test.assertEquals
import kotlin.test.Test


internal class CreateUserStoryViewModelTest {

    private fun viewModel() = CreateUserStoryViewModel()

    @Test
    fun `on init, should show empty user story`() {
        //WHEN
        val viewModel = viewModel()

        //THEN
        assertEquals(
            UserStory(
                Need(persona = "", wish = "", goal = ""),
            ),
            viewModel.uiState.value.userStory,
        )
    }

    @Test
    fun `on persona input change, should save value`() {
        //GIVEN
        val viewModel = viewModel()
        val input = "Skrillex"

        //WHEN
        viewModel.onInputChange(inputType = InputType.PERSONA, value = input)

        //THEN
        assertEquals(
            input,
            viewModel.uiState.value.userStory.need.persona,
        )
    }

    @Test
    fun `on wish input change, should save value`() {
        //GIVEN
        val viewModel = viewModel()
        val input = "add a button"

        //WHEN
        viewModel.onInputChange(inputType = InputType.WISH, value = input)

        //THEN
        assertEquals(
            input,
            viewModel.uiState.value.userStory.need.wish,
        )
    }

    @Test
    fun `on goal input change, should save value`() {
        //GIVEN
        val viewModel = viewModel()
        val input = "increase transaction"

        //WHEN
        viewModel.onInputChange(inputType = InputType.GOAL, value = input)

        //THEN
        assertEquals(
            input,
            viewModel.uiState.value.userStory.need.goal,
        )
    }
}
