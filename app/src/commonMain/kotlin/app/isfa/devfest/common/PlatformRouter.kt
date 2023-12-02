package app.isfa.devfest.common

interface PlatformRouter {

    fun navigateTo(url: String)
}

expect fun navigate(): PlatformRouter