package fr.mcgalanes.groomr.feature.createuserstory.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.EmojiObjects
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.PermMedia
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.QueryStats
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
            Step.Need -> Icons.Outlined.EmojiObjects
            Step.Kpi -> Icons.Outlined.QueryStats
            Step.Value -> Icons.Outlined.AutoAwesome
            Step.Solution -> Icons.Outlined.Psychology
            Step.Enablers -> Icons.Outlined.LockOpen
            Step.Assets -> Icons.Outlined.PermMedia
            Step.UAT -> Icons.Outlined.Checklist
        },
        step = this,
    )
