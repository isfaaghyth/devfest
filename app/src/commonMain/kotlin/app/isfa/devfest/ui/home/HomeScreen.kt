package app.isfa.devfest.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.isfa.devfest.ui.components.ChapterCard
import app.isfa.devfest.ui.components.HeadlineCard
import app.isfa.devfest.data.Chapter
import app.isfa.devfest.data.Topic

@Composable
fun HomeScreen(
    onCardClicked: (Chapter) -> Unit
) {
    val chapters by remember {
        mutableStateOf(
            listOf(
                Chapter("1", "GDG Jakarta", "loren ipsum", "https://sessionize.com/image/89eb-1140o400o3-PkzPudtsjdko7XtuMTpS9e.png", true, listOf(
                    Topic("1", "Android")
                )),
                Chapter("2", "GDG Jogjakarta", "loren ipsum", "https://sessionize.com/image/89eb-1140o400o3-PkzPudtsjdko7XtuMTpS9e.png", false, listOf(
                    Topic("1", "Android"), Topic("2", "Web")
                )),
                Chapter("4", "GDG Jakarta", "loren ipsum", "https://sessionize.com/image/89eb-1140o400o3-PkzPudtsjdko7XtuMTpS9e.png", true, listOf(
                    Topic("1", "Android")
                )),
                Chapter("5", "GDG Jogjakarta", "loren ipsum", "https://sessionize.com/image/89eb-1140o400o3-PkzPudtsjdko7XtuMTpS9e.png", false, listOf(
                    Topic("1", "Android"), Topic("2", "Web")
                )),
                Chapter("7", "GDG Jakarta", "loren ipsum", "https://sessionize.com/image/89eb-1140o400o3-PkzPudtsjdko7XtuMTpS9e.png", true, listOf(
                    Topic("1", "Android")
                )),
                Chapter("8", "GDG Jogjakarta", "loren ipsum", "", false, listOf(Topic("1", "Android"), Topic("2", "Web"))),
            )
        )
    }

    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(24.dp)
        ) {
            item { HeadlineCard("Malam") }
        }

        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(24.dp),
            columns = GridCells.Adaptive(minSize = 248.dp)
        ) {
            items(items = chapters, key = { it.id }) {
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