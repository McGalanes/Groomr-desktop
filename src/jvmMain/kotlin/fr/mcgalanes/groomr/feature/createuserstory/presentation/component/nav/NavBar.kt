package fr.mcgalanes.groomr.feature.createuserstory.presentation.component.nav

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.theme.AppTheme
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.StepItem
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem

@Preview
@Composable
fun NavBarPreview() {
    AppTheme {
        NavBar(
            items = Step.values().map { it.toStepItem(isSelected = false) },
            onSelectStep = {}
        )
    }
}

@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    items: List<StepItem>,
    onSelectStep: (Step) -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 4.dp,
    ) {
        Box {
            NavigationRail {
                items.forEach { item ->
                    NavigationRailItem(
                        modifier = Modifier.padding(vertical = 4.dp),
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                textAlign = TextAlign.Center,
                            )
                        },
                        selected = item.isSelected,
                        onClick = { onSelectStep(item.step) }
                    )
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter),
                onClick = {},
            ) {
                Icon(Icons.Filled.Share, "Partager")
            }
        }
    }
}
