package by.godevelopment.alfarssreader.data.localdatasource

import android.util.Log
import by.godevelopment.alfarssreader.commons.TAG
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
        Log.i(TAG, "NewsLocalDataSource insertRawNews: ${articlesEntity.size}")
        newsDao.insertAllArticleEntities(*articlesEntity)
    }

    suspend fun changeFavoriteStatusInArticleByUrl(key: String) {
        val article = newsDao.getArticleEntityByUrl(key)
        newsDao.replaceAllArticleEntities(
            article.copy(isFavorite = !article.isFavorite)
        )
    }

    fun getDbIsReadyStateAsFlow(): Flow<Boolean> = getAllNews().map {
        Log.i(TAG, "getDbIsReadyStateAsFlow: size = ${it.size}")
        !it.isNullOrEmpty()
    }
}
