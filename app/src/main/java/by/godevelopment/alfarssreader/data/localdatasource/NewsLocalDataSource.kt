package by.godevelopment.alfarssreader.data.localdatasource

import by.godevelopment.alfarssreader.data.datamodels.Article
import by.godevelopment.alfarssreader.data.datamodels.ArticleEntity
import by.godevelopment.alfarssreader.data.datautils.ConvertArticleApiToArticleEntity
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(
    private val newsDao: NewsDao,
    private val convertArticleApiToArticleEntity: ConvertArticleApiToArticleEntity
) {

    fun getAllNews() = newsDao.getAllArticleEntities()

    suspend fun insertAllNews(articles: List<ArticleEntity>) {
        newsDao.insertAllArticleEntities(*articles.toTypedArray())
    }

    suspend fun replaceAllNews(articles: List<ArticleEntity>) {
        newsDao.replaceAllArticleEntities(*articles.toTypedArray())
    }

    suspend fun insertRawNews(rawData: List<Article>) {
        val articles = rawData.map {
            convertArticleApiToArticleEntity(it)
        }
        newsDao.insertAllArticleEntities(*articles.toTypedArray())
    }
}