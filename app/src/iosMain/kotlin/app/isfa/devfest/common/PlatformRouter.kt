package app.isfa.devfest.common

import platform.Foundation.NSURL.Companion.URLWithString
import platform.UIKit.UIApplication

class NSPlatformRouter : PlatformRouter {

    override fun navigateTo(url: String) {
        val mUrl = URLWithString(url)?.absoluteURL() ?: return
        UIApplication.sharedApplication.openURL(mUrl)
    }
}

actual fun navigate(): PlatformRouter = NSPlatformRouter()