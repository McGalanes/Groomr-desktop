package fr.mcgalanes.groomr.feature.createuserstory.injection

import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.CreateUserStoryViewModel
import org.koin.dsl.module

fun createUserStoryModule() = module {
    // Domain
    single { GetStepsUseCase() }
    single { GetNextStepUseCase() }
    single { GetPreviousStepUseCase() }

    // Presentation
    single { CreateUserStoryViewModel(get(), get(), get()) }
}