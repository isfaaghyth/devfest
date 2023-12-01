@file:OptIn(ExperimentalResourceApi::class)

package app.isfa.devfest.data.repository

import app.isfa.devfest.data.entity.Chapter
import app.isfa.devfest.data.entity.GdgResponse
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

interface ChapterRepository {

    suspend fun chapters(): List<Chapter>
}

class ChapterRepositoryImpl : ChapterRepository {

    private val chapterJsonFile: String
        get() = "mock/chapters.json"

    override suspend fun chapters(): List<Chapter> {
        return try {
            val data = resource(chapterJsonFile).readBytes().decodeToString()
            val result = Json.decodeFromString<GdgResponse>(data)

            result.chapters
        } catch (t: Throwable) {
            emptyList()
        }
    }
}