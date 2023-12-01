package app.isfa.devfest.ui.home

sealed class HomeEvent

data object RequestData : HomeEvent()
data object GetWording : HomeEvent()