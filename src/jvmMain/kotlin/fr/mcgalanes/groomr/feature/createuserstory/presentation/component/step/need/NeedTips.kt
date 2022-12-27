package fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.need

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.mcgalanes.groomr.core.compose.theme.AppTheme

@Preview
@Composable
fun NeedTipsPreview() {
    AppTheme {
        NeedTips()
    }
}

@Composable
fun NeedTips(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text("Information about Need")
    }
}