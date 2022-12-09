package fr.mcgalanes.groomr.feature.createuserstory.presentation.state

import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.StepItem

data class UiState(
    val stepsItems: List<StepItem>,
    val stepForm: StepForm,
)
