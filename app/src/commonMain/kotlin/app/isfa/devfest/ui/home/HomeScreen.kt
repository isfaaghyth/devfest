package app.isfa.devfest.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.isfa.devfest.NavRouter
import app.isfa.devfest.androidx.compose.material3.windowsizeclass.LocalWindowSizeClass
import app.isfa.devfest.androidx.compose.material3.windowsizeclass.WindowSizeClass
import app.isfa.devfest.androidx.compose.material3.windowsizeclass.WindowWidthSizeClasses
import app.isfa.devfest.data.entity.Chapter
import app.isfa.devfest.ui.common.TwoPanelScaffold
import app.isfa.devfest.ui.common.TwoPanelScaffoldAnimationSpec
import app.isfa.devfest.ui.components.ChapterCard
import app.isfa.devfest.ui.components.HeadlineCard
import app.isfa.devfest.ui.detail.DetailScreen
import io.github.xxfast.decompose.router.rememberOnRoute

@Composable
fun HomeScreen(onCardClicked: (Chapter) -> Unit) {
    val viewModel: HomeViewModel = rememberOnRoute(HomeViewModel::class) { HomeViewModel() }
    val state by viewModel.state.collectAsState()

    var router: NavRouter? by rememberSaveable { mutableStateOf(null) }
    val details: NavRouter.Detail? = router as? NavRouter.Detail
    val windowSizeClass: WindowSizeClass = LocalWindowSizeClass.current
    var showSidePanel: Boolean by rememberSaveable { mutableStateOf(details != null) }

    LaunchedEffect(windowSizeClass) {
        router = router.takeIf { windowSizeClass.widthSizeClass != WindowWidthSizeClasses.Compact }
        showSidePanel = router != null
    }

    TwoPanelScaffold(
        panelVisibility = showSidePanel,
        animationSpec = TwoPanelScaffoldAnimationSpec(
            finishedListener = { fraction -> if (fraction == 1f) router = null }
        ),
        body = {
            HomeScreenView(
                state = state,
                onCardClicked = {
                    val detailSelection = NavRouter.Detail(it)

                    // If the screen size is compact (or mobile device screen size), then
                    // navigate to detail page with router. Otherwise, render the [panel].
                    if (windowSizeClass.widthSizeClass == WindowWidthSizeClasses.Compact) {
                        onCardClicked(it)
                        return@HomeScreenView
                    }

                    router = detailSelection
                    showSidePanel = true
                }
            )
        },
        panel = {
            Surface(tonalElevation = 1.dp) {
                if (details != null) {
                    DetailScreen(
                        chapter = details.chapter,
                        onBack = { showSidePanel = false }
                    )
                }
            }
        }
    )
}

@Composable
fun HomeScreenView(state: HomeUiState, onCardClicked: (Chapter) -> Unit) {
    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(24.dp)
        ) {
            item { HeadlineCard(state.currentTime) }
        }

        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(24.dp),
            columns = GridCells.Adaptive(minSize = 248.dp)
        ) {
            items(items = state.chapters, key = { it.id }) {
                ChapterCard(
                    chapter = it,
                    onClick = {
                        onCardClicked(it)
                    }
                )
            }
        }
    }
}