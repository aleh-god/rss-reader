package by.godevelopment.alfarssreader.data.repositories

import android.util.Log
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.data.datamodels.ArticleEntity
import by.godevelopment.alfarssreader.data.datautils.ConvertArticleApiToArticleEntity
import by.godevelopment.alfarssreader.data.localdatasource.NewsLocalDataSource
import by.godevelopment.alfarssreader.data.remotedatasource.NewsRemoteDataSource
import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import by.godevelopment.alfarssreader.domain.usecases.GetArticlesAndConvertToItemsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override fun getArticlesAsFlow(): Flow<List<ArticleEntity>> =
        newsLocalDataSource
            .getAllNews()
            .map { list ->
                list.ifEmpty {
                    val remoteData = newsRemoteDataSource.fetchLatestArticles()
                    newsLocalDataSource.insertRawNews(remoteData)
                    emptyList()
                }
            }

    override suspend fun setFavoriteStatusInArticleByUrl(key: String): Boolean {
        Log.i(TAG, "NewsRepositoryImpl saveNewsCardToFavorite: $key")
        newsLocalDataSource.setFavoriteStatusInArticleByUrl(key)
        return true
    }

    override suspend fun changeFavoriteStatusInArticleByUrl(key: String) {
        newsLocalDataSource.changeFavoriteStatusInArticleByUrl(key)
    }
}