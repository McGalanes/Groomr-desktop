@file:OptIn(ExperimentalComposeUiApi::class)

package fr.mcgalanes.groomr.feature.createuserstory.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.StepForm
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
                stepForm = StepForm.Need(
                    persona = "acheteur",
                    wish = "négocier le prix d'un article",
                    goal = "acheter au meilleur prix",
                ),
                stepsItems = Step.values().map {
                    it.toStepItem(isSelected = it == selectedStep)
                }
            ),
            onNavStepClick = {},
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
        onNavStepClick = createUserStoryViewModel::onNavStepClick,
        onNextClick = createUserStoryViewModel::onNextClick,
        onPreviousClick = createUserStoryViewModel::onPreviousClick,
        onStepFormStateChange = {}
    )
}

@Composable
private fun CreateUserStoryScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onNavStepClick: (Step) -> Unit,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onStepFormStateChange: (StepForm) -> Unit,
) {
    Row(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        NavBar(
            items = uiState.stepsItems,
            onSelectStep = onNavStepClick,
        )

        DualPanel(
            modifier = modifier.fillMaxSize(),
            primaryPanelContent = {
                FormsStepper(
                    modifier = Modifier.fillMaxSize(),
                    onNextClick = onNextClick,
                    onPreviousClick = onPreviousClick,
                ) {
                    when (val stepState = uiState.stepForm) {
                        is StepForm.Need -> {
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
                when (uiState.stepForm) {
                    is StepForm.Need -> NeedTips(Modifier.fillMaxSize())

                    else -> {}
                }
            },
        )
    }
}