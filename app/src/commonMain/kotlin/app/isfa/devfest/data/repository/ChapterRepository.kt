package app.isfa.devfest.data.repository

import app.isfa.devfest.data.entity.Chapter
import app.isfa.devfest.data.entity.Topic

interface ChapterRepository {

    fun chapters(): List<Chapter>
}

class ChapterRepositoryImpl : ChapterRepository {

    override fun chapters(): List<Chapter> {
        return listOf(
            Chapter("1", "GDG Jakarta", "loren ipsum", "https://sessionize.com/image/89eb-1140o400o3-PkzPudtsjdko7XtuMTpS9e.png", true, listOf(
                Topic("1", "Android")
            )),
            Chapter("2", "GDG Jogjakarta", "loren ipsum", "https://sessionize.com/image/89eb-1140o400o3-PkzPudtsjdko7XtuMTpS9e.png", false, listOf(
                Topic("1", "Android"), Topic("2", "Web")
            ))
        )
    }
}