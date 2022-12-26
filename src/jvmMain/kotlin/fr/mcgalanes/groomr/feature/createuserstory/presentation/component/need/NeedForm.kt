@file:OptIn(ExperimentalComposeUiApi::class)

package fr.mcgalanes.groomr.feature.createuserstory.presentation.component.need

import ExtractedStrings
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.component.spacer.HorizontalSpace
import fr.mcgalanes.groomr.core.compose.component.spacer.VerticalSpace
import fr.mcgalanes.groomr.core.compose.theme.AppTheme
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.NeedFormField

@Preview
@Composable
private fun NeedFormPreview() {
    Column {
        AppTheme(useDarkTheme = false) {
            NeedForm(
                modifier = Modifier.padding(16.dp),
                state = StepForm.Need("", "", ""),
                onFieldChange = { _, _ -> }
            )
        }

        HorizontalSpace(16.dp)

        AppTheme(useDarkTheme = true) {
            NeedForm(
                modifier = Modifier.padding(16.dp),
                state = StepForm.Need("", "", ""),
                onFieldChange = { _, _ -> }
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun NeedForm(
    modifier: Modifier = Modifier,
    state: StepForm.Need,
    onFieldChange: (NeedFormField, String) -> Unit,
) {
    StepForm(
        modifier = modifier,
    ) {
        Text(
            text = ExtractedStrings.createuserstory_needform_title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        VerticalSpace(24.dp)


        OutlinedTextField(
            label = { Text(text = ExtractedStrings.createuserstory_needform_persona_label) },
            placeholder = { Text(text = ExtractedStrings.createuserstory_needform_persona_placeholder) },
            value = state.persona,
            onValueChange = { onFieldChange(NeedFormField.Persona, it) },
        )

        VerticalSpace(16.dp)

        OutlinedTextField(
            label = { Text(text = ExtractedStrings.createuserstory_needform_wish_label) },
            placeholder = { Text(text = ExtractedStrings.createuserstory_needform_wish_placeholder) },
            value = state.wish,
            onValueChange = { onFieldChange(NeedFormField.Wish, it) },

            )

        VerticalSpace(16.dp)

        OutlinedTextField(
            label = { Text(text = ExtractedStrings.createuserstory_needform_goal_label) },
            placeholder = { Text(text = ExtractedStrings.createuserstory_needform_goal_placeholder) },
            value = state.goal,
            onValueChange = { onFieldChange(NeedFormField.Goal, it) },

            )
    }
}