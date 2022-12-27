package fr.mcgalanes.groomr.feature.createuserstory.injection

import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetUserStoryUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.SaveStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.CreateUserStoryViewModel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.kpi.KpiFormViewModel
import fr.mcgalanes.groomr.feature.createuserstory.presentation.component.step.need.NeedFormViewModel
import org.koin.dsl.module
import fr.mcgalanes.groomr.feature.createuserstory.data.repository.UserStoryRepository as DefaultUserStoryRepository

fun createUserStoryModule() = module {
    // Domain
    single { GetUserStoryUseCase(get()) }
    single { GetStepsUseCase() }
    single { GetNextStepUseCase() }
    single { GetPreviousStepUseCase() }
    single { GetStepFormUseCase(get()) }
    single { GetNextStepFormUseCase(get(), get()) }
    single { GetPreviousStepFormUseCase(get(), get()) }
    single { SaveStepFormUseCase(get()) }

    // Data
    single<UserStoryRepository> { DefaultUserStoryRepository() }

    // Presentation
    single { CreateUserStoryViewModel(get(), get(), get()) }
    single { NeedFormViewModel(get(), get()) }
    single { KpiFormViewModel(get(), get()) }
}