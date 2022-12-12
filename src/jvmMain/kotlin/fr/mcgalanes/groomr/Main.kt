package fr.mcgalanes.groomr

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import fr.mcgalanes.groomr.core.compose.theme.AppTheme
import fr.mcgalanes.groomr.feature.createuserstory.presentation.CreateUserStoryScreen
import fr.mcgalanes.groomr.injection.initKoin


fun main() = application {
    initKoin()

    Window(
        title = "Groomr",
        onCloseRequest = ::exitApplication,
    ) {
        App()
    }
}

@Composable
@Preview
fun App() {
    AppTheme(useDarkTheme = false) {
        CreateUserStoryScreen()
    }
}