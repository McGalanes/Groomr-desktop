@file:OptIn(ExperimentalComposeUiApi::class)

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import fr.mcgalanes.groomr.core.compose.component.spacer.VerticalSpace
import fr.mcgalanes.groomr.core.compose.theme.AppTheme
import fr.mcgalanes.groomr.feature.createuserstory.presentation.CreateUserStoryViewModel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.NeedForm

@ExperimentalComposeUiApi
@Composable
@Preview
fun App() {
    AppTheme(useDarkTheme = false) {
        val viewModel = remember { CreateUserStoryViewModel() }

        val uiState by viewModel.uiState.collectAsState()
        val need = uiState.userStory.need

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.TopCenter,
        ) {
            LazyColumn {
                item {
                    VerticalSpace(24.dp)

                    NeedForm(
                        personaInput = need.persona,
                        wishInput = need.wish,
                        goalInput = need.goal,
                        onInputChange = viewModel::onInputChange,
                    )
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
