package app.isfa.devfest.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.isfa.devfest.data.entity.Chapter
import app.isfa.devfest.ui.components.OrganizerCard

@Composable
fun DetailScreen(
    chapter: Chapter,
    onBack: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    LazyColumn(
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
        item {
            Column(
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        start = 24.dp
                    )
            ) {
                Text(
                    text = chapter.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 2.dp)
                )

                Text(
                    text = "Link",
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
                        .clickable {

                        }
                )
            }
        }

        item { Spacer(modifier = Modifier.padding(top = 36.dp)) }

        item {
            Text(
                text = "Organizers",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(
                        start = 24.dp
                    )
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    top = 12.dp,
                    start = 24.dp,
                    bottom = 24.dp
                )
            ) {
                items(chapter.organizers) { OrganizerCard(it) }

                item { Spacer(modifier = Modifier.padding(start = 4.dp, end = 20.dp)) }
            }
        }
    }
}
