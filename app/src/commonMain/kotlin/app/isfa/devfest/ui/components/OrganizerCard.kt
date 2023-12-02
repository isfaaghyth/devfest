package app.isfa.devfest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.isfa.devfest.data.entity.Organizer
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.orEmpty
import org.jetbrains.compose.resources.rememberImageBitmap
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun OrganizerCard(organizer: Organizer) {
    Column(
        modifier = Modifier
            .padding(2.dp)
            .widthIn(max = 140.dp)
            .height(200.dp)
    ) {
        Row {
            OrganizerAvatar(organizer.avatar)
        }
        Column(
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Column {
                Text(
                    text = organizer.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth(),
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = organizer.roleName(),
                        color = MaterialTheme.colorScheme.tertiary,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )

                    if (organizer.isWtm == true) {
                        Image(
                            bitmap = resource("drawable/ic_wtm.png")
                                .rememberImageBitmap()
                                .orEmpty(),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .sizeIn(maxHeight = 16.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}


@Composable
private fun OrganizerAvatar(url: String?) {
    if (url.isNullOrEmpty()) return

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        contentAlignment = Alignment.Center,
    ) {
        AutoSizeBox(url = url) { action ->
            when (action) {
                is ImageAction.Success -> {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop,
                        painter = rememberImageSuccessPainter(action),
                        contentDescription = null,
                    )
                }
                is ImageAction.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(64.dp),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
                is ImageAction.Failure -> {} // TODO: Placeholder
            }
        }
    }
}