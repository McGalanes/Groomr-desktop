@file:OptIn(ExperimentalComposeUiApi::class)

package fr.mcgalanes.groomr.feature.createuserstory.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.theme.AppTheme
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.DualPanel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.FormsStepper
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.nav.NavBar
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.need.NeedForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.need.NeedTips
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.StepFormState
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.StepsNavBarState
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UiState
import fr.mcgalanes.groomr.injection.get

@Preview
@Composable
private fun CreateUserStoryScreenPreview() {
    AppTheme {
        val steps = Step.values()
        val selectedStep = steps.random()

        CreateUserStoryScreen(
            uiState = UiState(
                stepFormState = StepFormState.Need(
                    persona = "acheteur",
                    wish = "négocier le prix d'un article",
                    goal = "acheter au meilleur prix",
                ),
                stepsNavBarState = StepsNavBarState(
                    items = Step.values()
                        .map {
                            StepsNavBarState.ItemState(
                                it.toStepItem(),
                                isSelected = it == selectedStep
                            )
                        }
                )
            ),
            onNextClick = {},
            onPreviousClick = {},
            onStepFormStateChange = {}
        )
    }
}

@Composable
fun CreateUserStoryScreen(
    modifier: Modifier = Modifier,
    createUserStoryViewModel: CreateUserStoryViewModel = get()
) {
    val uiState by createUserStoryViewModel.uiState.collectAsState()

    CreateUserStoryScreen(
        modifier = modifier,
        uiState = uiState,
        onNextClick = createUserStoryViewModel::onNextClick,
        onPreviousClick = createUserStoryViewModel::onPreviousClick,
        onStepFormStateChange = {}
    )
}

@Composable
private fun CreateUserStoryScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onStepFormStateChange: (StepFormState) -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        NavBar(
            state = uiState.stepsNavBarState,
            onSelectStep = {}
        )

        DualPanel(
            modifier = modifier.fillMaxSize(),
            primaryPanelContent = {
                FormsStepper(
                    modifier = Modifier.fillMaxSize(),
                    onNextClick = onNextClick,
                    onPreviousClick = onPreviousClick,
                ) {
                    when (val stepState = uiState.stepFormState) {
                        is StepFormState.Need -> {
                            NeedForm(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                state = stepState,
                                onFormChange = onStepFormStateChange
                            )
                        }

                        else -> {}
                    }
                }
            },
            secondaryPanelContent = {
                when (uiState.stepFormState) {
                    is StepFormState.Need -> NeedTips(Modifier.fillMaxSize())

                    else -> {}
                }
            },
        )
    }
}