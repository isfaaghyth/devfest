package app.isfa.devfest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.orEmpty
import org.jetbrains.compose.resources.rememberImageBitmap
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HeadlineCard(current: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val devFestIconResource = if (isSystemInDarkTheme()) {
            resource("drawable/ic_devfest_white.png")
        } else {
            resource("drawable/ic_devfest_black.png")
        }

        Image(
            bitmap = devFestIconResource
                .rememberImageBitmap()
                .orEmpty(),
            contentDescription = null,
            modifier = Modifier
                .sizeIn(maxHeight = 32.dp)
                .padding(bottom = 12.dp)
        )

        Text(
            text = "Selamat $current 👋🏼",
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Pilih chapter terdekatmu!",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 2.dp)
        )
    }
}