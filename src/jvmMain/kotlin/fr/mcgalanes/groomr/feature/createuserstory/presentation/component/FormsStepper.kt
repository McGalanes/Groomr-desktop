package fr.mcgalanes.groomr.feature.createuserstory.presentation.component

import ExtractedStrings
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.theme.AppTheme

@Preview
@Composable
fun FormsStepperPreview() {
    AppTheme {
        FormsStepper(
            onNextClick = {},
            onPreviousClick = {},
        ) { }
    }
}

@Composable
fun FormsStepper(
    modifier: Modifier = Modifier,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.weight(1f)) {
            content()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextButton(
                onClick = onPreviousClick,
            ) {
                Text(text = ExtractedStrings.createuserstory_formsstepper_action_previous)
            }

            Button(
                onClick = onNextClick,
            ) {
                Text(text = ExtractedStrings.createuserstory_formsstepper_action_next)
            }
        }
    }
}