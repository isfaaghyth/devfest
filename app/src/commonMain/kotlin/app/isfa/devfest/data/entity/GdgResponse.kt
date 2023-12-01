package app.isfa.devfest.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GdgResponse(
    @SerialName("chapters") val chapters: List<Chapter>,
    @SerialName("status") val status: Int
)