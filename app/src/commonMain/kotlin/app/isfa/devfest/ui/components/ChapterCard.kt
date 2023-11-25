package app.isfa.devfest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import app.isfa.devfest.data.entity.Chapter
import app.isfa.devfest.data.entity.Topic
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterCard(
    chapter: Chapter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        // Use custom label for accessibility services to communicate button's action to user.
        // Pass null for action to only override the label and not the actual action.
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                onClick(label = chapter.name, action = null)
            },
    ) {
        Column {
            Row {
                ChapterBannerImage(chapter.bannerUrl)
            }
            Box(
                modifier = Modifier.padding(16.dp),
            ) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        ChapterTitle(
                            chapter.name,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    ChapterOverviewDescription(chapter.content)
                    Spacer(modifier = Modifier.height(12.dp))
                    ChapterTopicList(
                        chapters = chapter.topics
                    )
                }
            }
        }
    }
}

@Composable
fun ChapterTitle(title: String, modifier: Modifier = Modifier) {
    Text(title, style = MaterialTheme.typography.headlineSmall, modifier = modifier)
}

@Composable
fun ChapterOverviewDescription(description: String) {
    Text(description, style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun ChapterTopicList(
    chapters: List<Topic>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()), // causes narrow chips
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (chapter in chapters) {
            ChapterTopic(
                text = {
                    Text(
                        text = chapter.name.uppercase(),
                        modifier = Modifier.semantics {
                            this.contentDescription = ""
                        },
                    )
                },
            )
        }
    }
}

@Composable
fun ChapterTopic(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        val containerColor = MaterialTheme.colorScheme.primaryContainer

        TextButton(
            onClick = {},
            enabled = enabled,
            colors = ButtonDefaults.textButtonColors(
                containerColor = containerColor,
                contentColor = contentColorFor(backgroundColor = containerColor)
            ),
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                text()
            }
        }
    }
}

@Composable
fun ChapterBannerImage(headerImageUrl: String?) {
    if (headerImageUrl.isNullOrEmpty()) return

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.Center,
    ) {
        AutoSizeBox(url = headerImageUrl) { action ->
            when (action) {
                is ImageAction.Success -> {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop,
                        painter = rememberImageSuccessPainter(action),
                        contentDescription = null, // decorative image,
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
    }
}