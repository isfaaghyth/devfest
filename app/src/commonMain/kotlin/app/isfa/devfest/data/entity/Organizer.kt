package app.isfa.devfest.data.entity

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Organizer(
    @SerialName("avatar") val avatar: String,
    @SerialName("is_wtm") val isWtm: Boolean? = false,
    @SerialName("name") val name: String,
    @SerialName("role") val role: Int
) : Parcelable {

    fun roleName(): String {
        return when (role) {
            0 -> if (isWtm == false) "Lead" else "Ambassador"
            1 -> "Co-Lead"
            else -> "Core Team"
        }
    }
}