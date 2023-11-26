@file:Suppress("unused", "FunctionName")

package app.isfa.devfest

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.ComposeUIViewController
import app.isfa.devfest.androidx.compose.material3.windowsizeclass.LocalWindowSizeClass
import app.isfa.devfest.androidx.compose.material3.windowsizeclass.WindowSizeClass
import app.isfa.devfest.androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import app.isfa.devfest.ui.common.DarkColorPalette
import app.isfa.devfest.ui.common.LightColorPalette
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext

fun MainViewController(routerContext: RouterContext) = ComposeUIViewController {
    BoxWithConstraints {
        val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
        val windowSizeClass: WindowSizeClass = calculateWindowSizeClass(DpSize(maxWidth, maxHeight))

        CompositionLocalProvider(
            LocalRouterContext provides routerContext,
            LocalWindowSizeClass provides windowSizeClass
        ) {
            MaterialTheme(
                colorScheme = colors
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DevFestApp()
                }
            }
        }
    }
}