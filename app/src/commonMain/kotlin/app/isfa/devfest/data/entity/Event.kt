package app.isfa.devfest.data.entity

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Event(
    @SerialName("date") val date: String,
    @SerialName("img") val img: String,
    @SerialName("link") val link: String,
    @SerialName("name") val name: String,
    @SerialName("topics") val topics: List<String>,
) : Parcelable