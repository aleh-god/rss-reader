package by.godevelopment.alfarssreader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import by.godevelopment.alfarssreader.data.datamodels.ArticleEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class NewsDaoTest : DatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertArticleEntityTest() = runBlocking {
        val articleEntity = ArticleEntity(
            isFavorite = false,
            storageUri = null,
            author = "author",
            description = "description",
            publishedAt = 0L,
            id = "1",
            name = "name",
            title = "title",
            url = "url",
            urlToImage = null,
            content = "content"
        )
        appDatabase.getNewsDao().insertAllArticleEntities(articleEntity)
        val newsSize = appDatabase.getNewsDao().getAllArticleEntities().first().size
        assertEquals(newsSize, 1)
    }

    @Test
    fun deleteQuoteTest() = runBlocking {
        val articleEntity = ArticleEntity(
            isFavorite = false,
            storageUri = null,
            author = "author",
            description = "description",
            publishedAt = 0L,
            id = "1",
            name = "name",
            title = "title",
            url = "url",
            urlToImage = null,
            content = "content"
        )
        appDatabase.getNewsDao().insertAllArticleEntities(articleEntity)
        appDatabase.getNewsDao().deleteArticleEntity(articleEntity)
        val newsSize = appDatabase.getNewsDao().getAllArticleEntities().first().size
        assertEquals(newsSize, 0)
    }

    @Test
    fun updateQuoteTest() = runBlocking {
        val articleEntity = ArticleEntity(
            isFavorite = false,
            storageUri = null,
            author = "author",
            description = "description",
            publishedAt = 0L,
            id = "1",
            name = "name",
            title = "title",
            url = "url",
            urlToImage = null,
            content = "content"
        )
        appDatabase.getNewsDao().insertAllArticleEntities(articleEntity)
        val updateArticleEntity = articleEntity.copy(
            author = "update"
        )
        appDatabase.getNewsDao().replaceAllArticleEntities(updateArticleEntity)
        val update = appDatabase.getNewsDao().getAllArticleEntities().first().first().author
        assertEquals(update, "update")
    }
}