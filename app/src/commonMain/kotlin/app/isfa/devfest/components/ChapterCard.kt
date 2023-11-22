package app.isfa.devfest.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import app.isfa.devfest.data.Chapter
import app.isfa.devfest.data.Topic

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
                onClick(label = "XXX", action = null)
            },
    ) {
        Column {
            Row {
                // ChapterBannerImage(chapter.bannerUrl)
            }
            Box(
                modifier = Modifier.padding(16.dp),
            ) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        ChapterTitle(
                            chapter.name,
                            modifier = Modifier.fillMaxWidth((.8f)),
                        )
                        // Spacer(modifier = Modifier.weight(1f))
                        // FollowButton(isBookmarked, onToggleBookmark)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // NewsResourceMetaData(userNewsResource.publishDate, userNewsResource.type)
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
fun ChapterTitle(
    newsResourceTitle: String,
    modifier: Modifier = Modifier,
) {
    Text(newsResourceTitle, style = MaterialTheme.typography.headlineSmall, modifier = modifier)
}

@Composable
fun ChapterOverviewDescription(
    newsResourceShortDescription: String,
) {
    Text(newsResourceShortDescription, style = MaterialTheme.typography.bodyLarge)
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

//@Composable
//fun ChapterBannerImage(
//    headerImageUrl: String?,
//) {
//    var isLoading by remember { mutableStateOf(true) }
//    var isError by remember { mutableStateOf(false) }
//    val imageLoader = rememberAsyncImagePainter(
//        model = headerImageUrl,
//        onState = { state ->
//            isLoading = state is AsyncImagePainter.State.Loading
//            isError = state is AsyncImagePainter.State.Error
//        },
//    )
//    val isLocalInspection = LocalInspectionMode.current
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(180.dp),
//        contentAlignment = Alignment.Center,
//    ) {
//        if (isLoading) {
//            // Display a progress bar while loading
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .size(80.dp),
//                color = MaterialTheme.colorScheme.tertiary,
//            )
//        }
//
//        Image(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(180.dp),
//            contentScale = ContentScale.Crop,
//            painter = if (isError.not() && !isLocalInspection) {
//                imageLoader
//            } else {
//                painterResource(drawable.ic_placeholder_default)
//            },
//            contentDescription = null, // decorative image,
//        )
//    }
//}