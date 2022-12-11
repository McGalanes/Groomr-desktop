package fr.mcgalanes.groomr.feature.createuserstory.injection

import fr.mcgalanes.groomr.feature.createuserstory.domain.repository.UserStoryRepository
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetNextStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetPreviousStepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepFormUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetStepsUseCase
import fr.mcgalanes.groomr.feature.createuserstory.domain.usecase.GetUserStoryUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.CreateUserStoryViewModel
import org.koin.dsl.module
import fr.mcgalanes.groomr.feature.createuserstory.data.repository.UserStoryRepository as DefaultUserStoryRepository

fun createUserStoryModule() = module {
    // Domain
    single { GetStepsUseCase() }
    single { GetUserStoryUseCase(get()) }
    single { GetNextStepUseCase() }
    single { GetPreviousStepUseCase() }
    single { GetStepFormUseCase(get()) }
    single<UserStoryRepository> { DefaultUserStoryRepository() }

    // Presentation
    single { CreateUserStoryViewModel(get(), get(), get(), get()) }
}