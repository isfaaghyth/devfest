package app.isfa.devfest.ui.home

import app.isfa.devfest.common.ViewModel
import app.isfa.devfest.data.repository.ChapterRepository
import app.isfa.devfest.data.repository.ChapterRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class HomeViewModel : ViewModel(), HomeViewModelContract {

    // TODO: Use koin
    private val repository: ChapterRepository = ChapterRepositoryImpl()

    private val _event = MutableSharedFlow<HomeEvent>(replay = 50)

    private val _state = MutableStateFlow(HomeUiState())
    override val state = _state.asStateFlow()

    init {
        launch {
            _event
                .distinctUntilChanged()
                .collect { event ->
                    when (event) {
                        is RequestData -> getChapterList()
                        is GetWording -> checkCurrentTimeForWording()
                    }
                }
        }
    }

    override fun setAction(event: HomeEvent) {
        _event.tryEmit(event)
    }

    private fun getChapterList() {
        launch {
            val data = repository.chapters()

            withContext(Dispatchers.Main) {
                _state.update { it.copy(chapters = data) }
            }
        }
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

    fun setAction(event: HomeEvent)
}