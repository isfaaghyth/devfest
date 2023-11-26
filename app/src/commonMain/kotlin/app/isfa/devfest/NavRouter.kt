package app.isfa.devfest

import app.isfa.devfest.data.entity.Chapter
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed class NavRouter : Parcelable {

    data object Home : NavRouter()
    data class Detail(val chapter: Chapter) : NavRouter()
}