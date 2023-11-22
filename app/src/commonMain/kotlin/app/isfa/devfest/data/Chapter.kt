package app.isfa.devfest.data

data class Chapter(
    val id: String,
    val name: String,
    val content: String,
    val bannerUrl: String,
    val isFollowed: Boolean,
    val topics: List<Topic>
)

data class Topic(
    val id: String,
    val name: String
)