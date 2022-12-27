package fr.mcgalanes.groomr.feature.createuserstory.presentation.state

import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.StepItem

data class UiState(
    val stepsItems: List<StepItem>,
    val step: Step,
)
