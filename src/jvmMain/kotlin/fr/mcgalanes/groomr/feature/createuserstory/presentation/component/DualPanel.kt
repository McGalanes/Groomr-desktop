package fr.mcgalanes.groomr.feature.createuserstory.presentation.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.theme.AppTheme

@Preview
@Composable
fun DualPanelPreview() {
    AppTheme {
        DualPanel(primaryPanelContent = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text("Primary Panel")
            }
        }, secondaryPanelContent = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text("Secondary Panel")
            }
        })
    }
}

@Composable
fun DualPanel(
    modifier: Modifier = Modifier,
    primaryPanelContent: @Composable () -> Unit,
    secondaryPanelContent: @Composable () -> Unit,
) {
    Row(
        modifier = modifier.background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        Box(
            modifier = Modifier.fillMaxHeight().width(500.dp),
        ) { secondaryPanelContent() }

        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 40.dp, bottomStart = 40.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
        ) { primaryPanelContent() }
    }
}