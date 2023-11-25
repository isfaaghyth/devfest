package app.isfa.devfest.ui.home

import app.isfa.devfest.common.ViewModel
import app.isfa.devfest.data.repository.ChapterRepository
import app.isfa.devfest.data.repository.ChapterRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class HomeViewModel : ViewModel(), HomeViewModelContract {

    // TODO: Use koin
    private val repository: ChapterRepository = ChapterRepositoryImpl()

    private val _state = MutableStateFlow(HomeUiState())
    override val state: StateFlow<HomeUiState> get() = _state

    init {
        checkCurrentTimeForWording()
        getChapterList()
    }

    private fun getChapterList() {
        _state.update { it.copy(chapters = repository.chapters()) }
    }

    private fun checkCurrentTimeForWording() {
        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        val message = when (currentTime.time.hour) {
            in 0..10 -> "Pagi"
            in 11..14 -> "Siang"
            in 15..18 -> "Sore"
            else -> "Malam"
        }

        _state.update { it.copy(currentTime = message) }
    }
}

interface HomeViewModelContract {

    val state: StateFlow<HomeUiState>
}