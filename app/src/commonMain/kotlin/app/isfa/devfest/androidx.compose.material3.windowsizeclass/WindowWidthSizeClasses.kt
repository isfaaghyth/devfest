package app.isfa.devfest.androidx.compose.material3.windowsizeclass

expect value class WindowWidthSizeClass private constructor(private val value: Int)

expect object WindowWidthSizeClasses {
    val Compact: WindowWidthSizeClass
    val Medium: WindowWidthSizeClass
    val Expanded: WindowWidthSizeClass
}
