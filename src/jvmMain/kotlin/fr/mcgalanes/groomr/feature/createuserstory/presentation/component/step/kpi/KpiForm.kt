@file:OptIn(ExperimentalComposeUiApi::class)

package fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.kpi

import ExtractedStrings
import androidx.compose.animation.animateContentSize
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.component.spacer.VerticalSpace
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.StepForm

@Preview
@Composable
private fun KpiFormPreview() {
    MaterialTheme {
        KpiForm(
            state = StepForm.Kpi(
                kpis = listOf(
                    "My first KPI",
                    "My second KPI",
                    "My third KPI",
                    "My fourth KPI",
                )
            ),
            onKpiChange = { _, _ -> },
            onNewKpiClick = {},
            onDeleteKpiClick = {},
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun KpiForm(
    modifier: Modifier = Modifier,
    state: StepForm.Kpi,
    onKpiChange: (index: Int, String) -> Unit,
    onNewKpiClick: () -> Unit,
    onDeleteKpiClick: (index: Int) -> Unit,
) {
    StepForm(modifier = modifier) {
        Text(
            text = ExtractedStrings.createuserstory_kpiform_title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        VerticalSpace(16.dp)

        Column(
            modifier = Modifier.fillMaxWidth().animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            state.kpis.forEachIndexed { index, text ->
                VerticalSpace(8.dp)

                Row {
                    KpiTextField(
                        value = text,
                        onValueChange = { onKpiChange(index, it) },
                        onRemoveClick = { onDeleteKpiClick(index) },
                    )
                }
            }
        }

        VerticalSpace(16.dp)

        FloatingActionButton(
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            onClick = onNewKpiClick,
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
            )
        }
    }
}

@Composable
private fun KpiTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onRemoveClick: () -> Unit,
) {
    TextField(
        modifier = modifier,
        placeholder = { Text(text = ExtractedStrings.createuserstory_kpiform_field_placeholder) },
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            FloatingActionButton(
                modifier = Modifier.size(24.dp),
                shape = CircleShape,
                onClick = onRemoveClick,
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Add",
                )
            }
        }
    )
}