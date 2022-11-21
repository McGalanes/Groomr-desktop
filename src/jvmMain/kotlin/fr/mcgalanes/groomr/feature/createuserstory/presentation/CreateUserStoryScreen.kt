package fr.mcgalanes.groomr.feature.createuserstory.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.mcgalanes.groomr.core.compose.theme.AppTheme
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.DualPanel

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
                              
        },
        secondaryPanelContent = {

        },
    )
}