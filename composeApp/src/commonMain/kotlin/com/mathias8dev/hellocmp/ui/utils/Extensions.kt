import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatform

@Composable
inline fun <reified T : Any> koinInject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    return remember(qualifier, parameters) {
        KoinPlatform.getKoin().get<T>(qualifier, parameters)
    }
}
