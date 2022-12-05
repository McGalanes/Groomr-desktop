package fr.mcgalanes.groomr.feature.createuserstory.injection

import fr.mcgalanes.groomr.feature.createuserstory.domain.StepUseCase
import fr.mcgalanes.groomr.feature.createuserstory.presentation.CreateUserStoryViewModel
import org.koin.dsl.module

fun createUserStoryModule() = module {
    single { StepUseCase() }
    single { CreateUserStoryViewModel(get()) }
}