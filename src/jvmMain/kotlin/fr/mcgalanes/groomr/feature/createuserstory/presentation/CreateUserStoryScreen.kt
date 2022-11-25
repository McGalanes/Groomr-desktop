@file:OptIn(ExperimentalComposeUiApi::class)

package fr.mcgalanes.groomr.feature.createuserstory.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.theme.AppTheme
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.DualPanel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.FormsStepper
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.need.NeedForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.need.NeedTips

@Preview
@Composable
private fun CreateUserStoryScreenPreview() {
    AppTheme {
        CreateUserStoryScreen()
    }
}

@Composable
fun CreateUserStoryScreen(modifier: Modifier = Modifier) {
    val viewModel = remember { CreateUserStoryViewModel() }

    val uiState by viewModel.uiState.collectAsState()
    val need = uiState.userStory.need

    DualPanel(
        modifier = modifier.fillMaxSize(),
        primaryPanelContent = {
            FormsStepper(
                modifier = Modifier.fillMaxSize(),
                onNextClick = {},
                onPreviousClick = {},
            ) {
                NeedForm(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    personaInput = need.persona,
                    wishInput = need.wish,
                    goalInput = need.goal,
                    onInputChange = viewModel::onInputChange,
                )
            }
        },
        secondaryPanelContent = {
            NeedTips(Modifier.fillMaxSize())
        },
    )
}