package fr.mcgalanes.groomr.injection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

@Composable
fun getKoin(): Koin = remember {
    GlobalContext.get()
}

@OptIn(KoinInternalApi::class)
@Composable
inline fun <reified T> get(
    qualifier: Qualifier? = null,
    scope: Scope = GlobalContext.get().scopeRegistry.rootScope,
    noinline parameters: ParametersDefinition? = null,
): T = remember(qualifier, parameters) {
    scope.get(qualifier, parameters)
}
