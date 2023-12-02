package app.isfa.devfest.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.isfa.devfest.common.navigate
import app.isfa.devfest.data.entity.Chapter
import app.isfa.devfest.data.entity.Event
import app.isfa.devfest.data.entity.Organizer
import app.isfa.devfest.ui.components.EventCard
import app.isfa.devfest.ui.components.OrganizerCard
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox

@Composable
fun DetailScreen(
    chapter: Chapter,
    onBack: () -> Unit
) {
    // interactionSource uses for remove the ripple effect in `onBack()`.
    val interactionSource = remember { MutableInteractionSource() }
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
    val state = rememberLazyListState()

    val chapterHeaderVisible by remember {
        derivedStateOf {
            state.firstVisibleItemIndex == 0
        }
    }

    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, MaterialTheme.colorScheme.surface),
        startY = sizeImage.height.toFloat() / 3,  // 1/3
        endY = sizeImage.height.toFloat()
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AutoSizeBox(url = chapter.banner) { action ->
            when (action) {
                is ImageAction.Success -> {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(if (chapterHeaderVisible) 0.3f else 0f)
                            .onGloballyPositioned {
                                sizeImage = it.size
                            },
                        contentScale = ContentScale.Crop,
                        painter = rememberImageSuccessPainter(action),
                        contentDescription = null,
                    )
                }
                is ImageAction.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(80.dp),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
                is ImageAction.Failure -> {} // TODO: Placeholder
            }
        }

        Box(modifier = Modifier.matchParentSize().background(gradient))
    }

    LazyColumn(
        state = state,
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onBack()
            }
    ) {
        // chapter header
        item { ChapterTitle(chapter.name, chapter.link) }

        // organizer list
        item { OrganizersSection(chapter.organizers) }

        // event list
        eventsSection(chapter.events)

        // empty space for last item
        item { Spacer(modifier = Modifier.padding(bottom = 24.dp)) }
    }
}

@Composable
private fun OrganizersSection(organizers: List<Organizer>) {
    Column {
        SectionTitle(
            title = "Organizers",
            topPadding = 36
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                top = 12.dp,
                start = 24.dp,
                bottom = 24.dp
            )
        ) {
            items(organizers) { OrganizerCard(it) }

            // padding decoration at last item
            item { Spacer(modifier = Modifier.padding(start = 4.dp, end = 20.dp)) }
        }
    }
}

private fun LazyListScope.eventsSection(events: List<Event>) {
    item { SectionTitle(title = "Events") }

    events.forEach { event ->
        item {
            EventCard(
                event = event,
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 12.dp
                    )
            )
        }
    }
}

@Composable
private fun SectionTitle(
    title: String,
    topPadding: Int = 0,
    modifier: Modifier = Modifier
) {
    Column {
        Spacer(modifier = Modifier.padding(top = topPadding.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .padding(
                    start = 24.dp
                )
        )
    }
}

@Composable
private fun ChapterTitle(name: String, bevyLink: String) {
    Column(
        modifier = Modifier
            .padding(
                top = 24.dp,
                start = 24.dp
            )
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 2.dp)
        )

        Text(
            text = "↖ Bevy Link",
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .drawBehind {
                    val strokeWidthPx = 1.dp.toPx()
                    val verticalOffset = size.height - 2.sp.toPx()
                    drawLine(
                        color = Color.Green,
                        strokeWidth = strokeWidthPx,
                        start = Offset(0f, verticalOffset),
                        end = Offset(size.width, verticalOffset)
                    )
                }
                .clickable { navigate().navigateTo(bevyLink) }
        )
    }
}
