@file:Suppress("unused", "FunctionName")

package app.isfa.devfest

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import app.isfa.devfest.ui.common.DarkColorPalette
import app.isfa.devfest.ui.common.LightColorPalette
import app.isfa.devfest.ui.common.PlatformConfig
import app.isfa.devfest.ui.common.PlatformConfigProvider
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext

fun MainViewController(routerContext: RouterContext) = ComposeUIViewController {
    val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
    val platform = PlatformConfig(isDarkTheme = isSystemInDarkTheme())

    CompositionLocalProvider(
        LocalRouterContext provides routerContext,
        PlatformConfigProvider provides platform
    ) {
        MaterialTheme(
            colorScheme = colors
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                DevFestApp(isSystemInDarkTheme())
            }
        }
    }
}