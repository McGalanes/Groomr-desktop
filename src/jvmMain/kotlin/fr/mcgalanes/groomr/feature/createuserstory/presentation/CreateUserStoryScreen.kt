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
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.DualPanel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.FormsStepper
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.nav.NavBar
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.kpi.KpiForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.need.NeedForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.need.NeedTips
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.value.BusinessValueForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
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
            onPersonaChange = {},
            onWishChange = {},
            onGoalChange = {},
            onKpiChange = {},
            onBusinessValueChange = {},
        )
    }
}

@Composable
fun CreateUserStoryScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateUserStoryViewModel = get()
) {
    val uiState by viewModel.uiState.collectAsState()

    CreateUserStoryScreen(
        modifier = modifier,
        uiState = uiState,
        onNavStepClick = viewModel::onNavStepClick,
        onNextClick = viewModel::onNextClick,
        onPreviousClick = viewModel::onPreviousClick,
        onPersonaChange = viewModel::onPersonaChange,
        onWishChange = viewModel::onWishChange,
        onGoalChange = viewModel::onGoalChange,
        onKpiChange = viewModel::onKpiChange,
        onBusinessValueChange = viewModel::onBusinessValueChange,
    )
}

@Composable
private fun CreateUserStoryScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onNavStepClick: (Step) -> Unit,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onPersonaChange: (String) -> Unit,
    onWishChange: (String) -> Unit,
    onGoalChange: (String) -> Unit,
    onKpiChange: (String) -> Unit,
    onBusinessValueChange: (String) -> Unit,
) {
    Row(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        NavBar(
            items = uiState.stepsItems,
            onItemClick = onNavStepClick,
            onFabClick = { TODO() }
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
                                onPersonaChange = onPersonaChange,
                                onWishChange = onWishChange,
                                onGoalChange = onGoalChange,
                            )
                        }

                        is StepForm.Kpi -> {
                            KpiForm(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                state = stepState,
                                onKpiChange = onKpiChange,
                            )
                        }

                        is StepForm.Value -> {
                            BusinessValueForm(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                state = stepState,
                                onBusinessValueChange = onBusinessValueChange,
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