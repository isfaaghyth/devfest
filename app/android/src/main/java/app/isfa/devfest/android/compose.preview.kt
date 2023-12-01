package app.isfa.devfest.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.isfa.devfest.data.entity.Chapter
import app.isfa.devfest.data.entity.Organizer
import app.isfa.devfest.ui.components.OrganizerCard
import app.isfa.devfest.ui.detail.DetailScreen

@Composable
@Preview
fun DetailScreenPreview() {
    DetailScreen(
        chapter = Chapter(
            "",
            listOf(),
            "",
            "GDG Jakarta",
            listOf(
                Organizer(
                    avatar = "",
                    isWtm = true,
                    name = "Loren Ipsum",
                    role = 0
                ),
                Organizer(
                    avatar = "",
                    isWtm = false,
                    name = "Foo Bar",
                    role = 1
                ),
                Organizer(
                    avatar = "",
                    isWtm = true,
                    name = "Loren Ipsum",
                    role = 0
                ),
                Organizer(
                    avatar = "",
                    isWtm = false,
                    name = "Foo Bar",
                    role = 1
                ),
                Organizer(
                    avatar = "",
                    isWtm = true,
                    name = "Loren Ipsum",
                    role = 0
                ),
                Organizer(
                    avatar = "",
                    isWtm = false,
                    name = "Foo Bar",
                    role = 1
                )
            )
        ),
        onBack = {}
    )
}

@Composable
@Preview
fun OrganizerCardPreview() {
    OrganizerCard(
        Organizer(
            avatar = "",
            isWtm = false,
            name = "Foo Bar",
            role = 1
        )
    )
}