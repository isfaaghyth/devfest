package app.isfa.devfest

import androidx.compose.runtime.Composable
import app.isfa.devfest.ui.detail.DetailScreen
import app.isfa.devfest.ui.home.HomeScreen
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import io.github.xxfast.decompose.router.rememberRouter

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun DevFestApp() {
    val router: Router<NavRouter> = rememberRouter(NavRouter::class) { listOf(NavRouter.Home) }

    RoutedContent(
        router = router,
        animation = predictiveBackAnimation(
            backHandler = LocalRouterContext.current.backHandler,
            onBack = { router.pop() },
            animation = stackAnimation(slide())
        )
    ) { screen ->
        when (screen) {
            is NavRouter.Home -> HomeScreen { router.push(NavRouter.Detail(it)) }
            is NavRouter.Detail -> DetailScreen(screen.chapter) { router.pop() }
        }
    }
}