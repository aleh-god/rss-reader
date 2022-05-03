package by.godevelopment.alfarssreader.data.localdatasource

import by.godevelopment.alfarssreader.data.datamodels.Article
import by.godevelopment.alfarssreader.data.datautils.ConvertArticleApiToArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(
    private val newsDao: NewsDao,
    private val convertArticleApiToArticleEntity: ConvertArticleApiToArticleEntity
) {

    fun getAllNews() = newsDao.getAllArticleEntities()

    suspend fun insertRawNews(rawRemoteData: List<Article>) {
        val articlesEntity = rawRemoteData
            .map { convertArticleApiToArticleEntity(it) }
            .toTypedArray()
        newsDao.insertAllArticleEntities(*articlesEntity)
    }

    suspend fun changeFavoriteStatusInArticleByUrl(key: String) {
        val article = newsDao.getArticleEntityByUrl(key)
        newsDao.replaceAllArticleEntities(
            article.copy(isFavorite = !article.isFavorite)
        )
    }

    fun getDbIsReadyStateAsFlow(): Flow<Boolean> = getAllNews().map {
        !it.isNullOrEmpty()
    }
}
