package app.isfa.devfest.ui.common

import androidx.compose.runtime.compositionLocalOf

data class PlatformConfig(
    val isDarkTheme: Boolean
)

val PlatformConfigProvider = compositionLocalOf { PlatformConfig(false) }