package fr.mcgalanes.groomr.feature.createuserstory.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.EmojiObjects
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.PermMedia
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.ui.graphics.vector.ImageVector
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step

data class StepItem(
    val label: String,
    val icon: ImageVector,
    val step: Step,
)

fun Step.toStepItem() =
    StepItem(
        label = this.name,
        icon = when (this) {
            Step.Need -> Icons.Default.EmojiObjects
            Step.Kpi -> Icons.Default.QueryStats
            Step.Value -> Icons.Default.AutoAwesome
            Step.Solution -> Icons.Default.Psychology
            Step.Enablers -> Icons.Default.LockOpen
            Step.Assets -> Icons.Default.PermMedia
            Step.UAT -> Icons.Default.Checklist
        },
        step = this,
    )
