package app.isfa.devfest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform