package fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.value

import ExtractedStrings
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.component.spacer.VerticalSpace
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.StepForm


@ExperimentalComposeUiApi
@Composable
fun BusinessValueForm(
    modifier: Modifier = Modifier,
    state: StepForm.Value,
    onBusinessValueChange: (String) -> Unit,
) {
    StepForm(
        modifier = modifier,
    ) {
        Text(
            text = ExtractedStrings.createuserstory_valueform_title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        VerticalSpace(24.dp)

        OutlinedTextField(
            label = { Text(text = ExtractedStrings.createuserstory_valueform_field_label) },
            value = state.businessValue?.toString() ?: "",
            onValueChange = { onBusinessValueChange(it.take(3)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}