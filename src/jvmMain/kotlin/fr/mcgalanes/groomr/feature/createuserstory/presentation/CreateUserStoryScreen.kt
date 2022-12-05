package fr.mcgalanes.groomr.feature.createuserstory.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.theme.AppTheme
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.DualPanel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.FormsStepper
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.need.NeedForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.need.NeedTips
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.StepState
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UiState
import fr.mcgalanes.groomr.injection.get

@Preview
@Composable
private fun CreateUserStoryScreenPreview() {
    AppTheme {
        CreateUserStoryScreen(
            uiState = UiState(
                stepState = StepState.Need("", "", "")
            ),
            onNextClick = { },
            onPreviousClick = { },
        )
    }
}

@Composable
fun CreateUserStoryRoute(
    viewModel: CreateUserStoryViewModel = get(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CreateUserStoryScreen(
        uiState = uiState,
        onNextClick = viewModel::onNextClick,
        onPreviousClick = viewModel::onPreviousClick,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateUserStoryScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
) {
    DualPanel(
        modifier = modifier.fillMaxSize(),
        primaryPanelContent = {
            FormsStepper(
                modifier = Modifier.fillMaxSize(),
                onNextClick = onNextClick,
                onPreviousClick = onPreviousClick,
            ) {
                when (val stepState = uiState.stepState) {
                    is StepState.Need -> {
                        NeedForm(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            personaInput = stepState.persona,
                            wishInput = stepState.wish,
                            goalInput = stepState.goal,
                            onInputChange = { _, _ -> },
                        )
                    }

                    else -> {}
                }
            }
        },
        secondaryPanelContent = {
            when (uiState.stepState) {
                is StepState.Need -> NeedTips(Modifier.fillMaxSize())

                else -> {}
            }

        },
    )
}