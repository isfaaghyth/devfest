package app.isfa.devfest.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import app.isfa.devfest.common.navigate
import app.isfa.devfest.data.entity.Event

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventCard(
    event: Event,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        onClick = { navigate().navigateTo(event.link) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                onClick(label = event.name, action = null)
            },
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
        ) {
            Column {
                Text(
                    text = event.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = event.date,
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                TopicList(topics = event.topics)
            }
        }
    }
}

@Composable
private fun TopicList(
    topics: List<String>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()), // causes narrow chips
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (topic in topics) {
            Topic(
                text = {
                    Text(
                        text = topic.uppercase(),
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
private fun Topic(
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
