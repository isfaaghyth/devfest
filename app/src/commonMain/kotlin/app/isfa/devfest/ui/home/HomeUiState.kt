package app.isfa.devfest.ui.home

import app.isfa.devfest.data.entity.Chapter

data class HomeUiState(
    val currentTime: String = "",
    val chapters: List<Chapter> = emptyList()
)