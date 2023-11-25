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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.isfa.devfest.data.entity.Chapter
import app.isfa.devfest.ui.components.ChapterCard
import app.isfa.devfest.ui.components.HeadlineCard
import io.github.xxfast.decompose.router.rememberOnRoute

@Composable
fun HomeScreen(onCardClicked: (Chapter) -> Unit) {
    val viewModel: HomeViewModel = rememberOnRoute(HomeViewModel::class) { HomeViewModel() }
    val state by viewModel.state.collectAsState()

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