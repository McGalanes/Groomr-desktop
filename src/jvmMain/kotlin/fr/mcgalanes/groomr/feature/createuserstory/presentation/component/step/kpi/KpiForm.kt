package fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.kpi

import ExtractedStrings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.component.spacer.VerticalSpace
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.StepForm


@ExperimentalComposeUiApi
@Composable
fun KpiForm(
    modifier: Modifier = Modifier,
    state: StepForm.Kpi,
    onKpiChange: (String) -> Unit,
) {
    StepForm(
        modifier = modifier,
    ) {
        Text(
            text = ExtractedStrings.createuserstory_kpiform_title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        VerticalSpace(24.dp)

        OutlinedTextField(
            label = { Text(text = ExtractedStrings.createuserstory_kpiform_field_label) },
            placeholder = { Text(text = ExtractedStrings.createuserstory_kpiform_field_placeholder) },
            value = state.kpi,
            onValueChange = onKpiChange,
        )
    }
}