package app.isfa.devfest.android

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.isfa.devfest.DevFestApp
import app.isfa.devfest.ui.common.DarkColorPalette
import app.isfa.devfest.ui.common.LightColorPalette
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext
import io.github.xxfast.decompose.router.defaultRouterContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
            val router = defaultRouterContext()

            CompositionLocalProvider(
                LocalRouterContext provides defaultRouterContext()
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
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun DevFestHomePreview() {
    val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(
        LocalRouterContext provides RouterContext(LifecycleRegistry())
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
