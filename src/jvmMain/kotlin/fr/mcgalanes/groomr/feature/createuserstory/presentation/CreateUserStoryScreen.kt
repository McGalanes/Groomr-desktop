package fr.mcgalanes.groomr.feature.createuserstory.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.mcgalanes.groomr.core.compose.theme.AppTheme
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.DualPanel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.FormsStepper

@Preview
@Composable
private fun CreateUserStoryScreenPreview() {
    AppTheme {
        CreateUserStoryScreen()
    }
}

@Composable
fun CreateUserStoryScreen(modifier: Modifier = Modifier) {
    DualPanel(
        modifier = modifier,
        primaryPanelContent = {
            FormsStepper(
                modifier = Modifier.fillMaxSize(),
                onNextClick = {},
                onPreviousClick = {}
            ) {

            }
        },
        secondaryPanelContent = {

        },
    )
}