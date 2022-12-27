@file:OptIn(ExperimentalComposeUiApi::class)

package fr.mcgalanes.groomr.feature.createuserstory.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mcgalanes.groomr.core.compose.theme.AppTheme
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.Step
import fr.mcgalanes.groomr.feature.createuserstory.domain.model.StepForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.DualPanel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.FormsStepper
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.nav.NavBar
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.kpi.KpiForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.kpi.KpiFormViewModel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.need.NeedForm
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.need.NeedFormViewModel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.need.NeedTips
import fr.mcgalanes.groomr.feature.createuserstory.presentation.model.toStepItem
import fr.mcgalanes.groomr.feature.createuserstory.presentation.state.UiState
import fr.mcgalanes.groomr.injection.get

@Preview
@Composable
private fun CreateUserStoryScreenPreview() {
    AppTheme {
        val steps = Step.values()
        val selectedStep = steps.random()

        CreateUserStoryScreen(
            uiState = UiState(
                stepsItems = Step.values().map {
                    it.toStepItem(isSelected = it == selectedStep)
                },
                step = selectedStep,
            ),
            onNavStepClick = {},
            onNextClick = {},
            onPreviousClick = {},
            needForm = StepForm.Need(
                persona = "acheteur",
                wish = "négocier le prix d'un article",
                goal = "acheter au meilleur prix",
            ),
            onPersonaChange = {},
            onWishChange = {},
            onGoalChange = {},
            kpiForm = StepForm.Kpi(""),
            onKpiChange = {},
            onBusinessValueChange = {},
        )
    }
}

@Composable
fun CreateUserStoryScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateUserStoryViewModel = get(),
    needFormViewModel: NeedFormViewModel = get(),
    kpiFormViewModel: KpiFormViewModel = get(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val needForm by needFormViewModel.formState.collectAsState()
    val kpiForm by kpiFormViewModel.formState.collectAsState()

    CreateUserStoryScreen(
        modifier = modifier,
        uiState = uiState,
        onNavStepClick = viewModel::onNavStepClick,
        onNextClick = viewModel::onNextClick,
        onPreviousClick = viewModel::onPreviousClick,
        needForm = needForm,
        onPersonaChange = needFormViewModel::onPersonaChange,
        onWishChange = needFormViewModel::onWishChange,
        onGoalChange = needFormViewModel::onGoalChange,
        kpiForm = kpiForm,
        onKpiChange = kpiFormViewModel::onKpiChange,
        onBusinessValueChange = {},
    )
}

@Composable
private fun CreateUserStoryScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onNavStepClick: (Step) -> Unit,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    needForm: StepForm.Need,
    onPersonaChange: (String) -> Unit,
    onWishChange: (String) -> Unit,
    onGoalChange: (String) -> Unit,
    kpiForm: StepForm.Kpi,
    onKpiChange: (String) -> Unit,
    onBusinessValueChange: (String) -> Unit,
) {
    Row(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        NavBar(
            items = uiState.stepsItems,
            onItemClick = onNavStepClick,
            onFabClick = { TODO() }
        )

        DualPanel(
            modifier = modifier.fillMaxSize(),
            primaryPanelContent = {
                FormsStepper(
                    modifier = Modifier.fillMaxSize(),
                    onNextClick = onNextClick,
                    onPreviousClick = onPreviousClick,
                ) {
                    when (uiState.step) {
                        Step.Need ->
                            NeedForm(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                state = needForm,
                                onPersonaChange = onPersonaChange,
                                onWishChange = onWishChange,
                                onGoalChange = onGoalChange,
                            )

                        else ->
                            KpiForm(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                state = kpiForm,
                                onKpiChange = onKpiChange,
                            )
                    }
                }
            },
            secondaryPanelContent = {
                when (uiState.step) {
                    Step.Need -> NeedTips(Modifier.fillMaxSize())
                    else -> Unit
                }
            },
        )
    }
}