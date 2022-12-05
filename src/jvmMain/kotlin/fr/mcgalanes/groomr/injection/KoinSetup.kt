package fr.mcgalanes.groomr.injection

import fr.mcgalanes.groomr.feature.createuserstory.injection.createUserStoryModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin() =
    startKoin {
        modules(
            *features(
                createUserStoryModule(),
            ),
        )
    }

private fun features(vararg featureModules: Module) = featureModules