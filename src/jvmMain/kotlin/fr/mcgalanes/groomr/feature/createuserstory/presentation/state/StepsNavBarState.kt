package fr.mcgalanes.groomr.feature.createuserstory.presentation.state

import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.StepItem

data class StepsNavBarState(
    val items: List<ItemState>
) {
    data class ItemState(
        val item: StepItem,
        val isSelected: Boolean,
    )
}
