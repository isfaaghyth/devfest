package app.isfa.devfest.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.lang.ref.WeakReference

class AndroidPlatformRouter constructor(
    private val context: Context?
) : PlatformRouter {

    override fun navigateTo(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context?.startActivity(intent)
    }
}

// TODO: Use a proper way
object WeakContextWrapper {

    private var context: WeakReference<Context>? = null

    fun set(context: Context) {
        this.context = WeakReference(context)
    }

    fun get(): Context? {
        return context?.get()
    }
}

actual fun navigate(): PlatformRouter = AndroidPlatformRouter(
    WeakContextWrapper.get()
)