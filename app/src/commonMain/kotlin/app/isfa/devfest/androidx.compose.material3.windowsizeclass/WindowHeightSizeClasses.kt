package app.isfa.devfest.androidx.compose.material3.windowsizeclass

expect value class WindowHeightSizeClass private constructor(private val value: Int)

expect object WindowHeightSizeClasses {
    val Compact: WindowHeightSizeClass
    val Medium: WindowHeightSizeClass
    val Expanded: WindowHeightSizeClass
}
