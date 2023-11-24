package app.isfa.devfest.data

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class Chapter(
    val id: String,
    val name: String,
    val content: String,
    val bannerUrl: String,
    val isFollowed: Boolean,
    val topics: List<Topic>
) : Parcelable

@Parcelize
data class Topic(
    val id: String,
    val name: String
) : Parcelable