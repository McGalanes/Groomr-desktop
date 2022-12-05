package fr.mcgalanes.groomr.feature.createuserstory.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager

@ExperimentalComposeUiApi
@Composable
fun StepForm(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .onKeyPressedDown { key ->
                when (key) {
                    Key.Escape -> {
                        focusManager.clearFocus()
                        true
                    }

                    Key.Tab -> {
                        focusManager
                            .moveFocus(FocusDirection.Down)
                            .also { succeed ->
                                if (!succeed) focusManager.clearFocus()
                            }
                        true
                    }

                    else -> false
                }

            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}

private fun Modifier.onKeyPressedDown(
    onKeyPressed: (Key) -> Boolean,
) =
    this.onPreviewKeyEvent {
        if (it.type == KeyEventType.KeyDown) {
            onKeyPressed(it.key)
        } else {
            false
        }
    }
