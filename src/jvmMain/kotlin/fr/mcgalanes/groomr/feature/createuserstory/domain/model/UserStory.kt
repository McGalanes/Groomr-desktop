package fr.mcgalanes.groomr.feature.createuserstory.domain.model

data class UserStory(
    val persona: String,
    val wish: String,
    val goal: String,
    val kpi: String,
    val businessValue: Int,
    val solution: String,
    val enablers: List<String>,
    val assets: List<String>,
    val uats: List<Uat>,
    val effectivity: Effectivity,
) {
    data class Uat(
        val title: String,
        val givens: List<String>,
        val action: String,
        val assertions: String,
    )

    data class Effectivity(
        val isSmallEnough: Boolean,
        val isIndependent: Boolean,
        val isEstimable: Boolean,
        val isTestable: Boolean,
    )
}