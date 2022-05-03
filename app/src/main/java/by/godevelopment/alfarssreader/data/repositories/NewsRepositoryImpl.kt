package by.godevelopment.alfarssreader.data.repositories

import by.godevelopment.alfarssreader.ui.activities.MainSplashRepository
import by.godevelopment.alfarssreader.data.datamodels.ArticleEntity
import by.godevelopment.alfarssreader.data.localdatasource.NewsLocalDataSource
import by.godevelopment.alfarssreader.data.remotedatasource.NewsRemoteDataSource
import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository, MainSplashRepository {

    override fun getArticlesAsFlow(): Flow<List<ArticleEntity>> =
        newsLocalDataSource.getAllNews()

    override suspend fun reloadNewsFromRemoteToLocalDataSource() {
        val remoteData = newsRemoteDataSource.fetchLatestArticles()
        newsLocalDataSource.insertRawNews(remoteData)
    }

    override fun getDbIsReadyStateAsFlow(): Flow<Boolean> =
        newsLocalDataSource.getDbIsReadyStateAsFlow()

    override suspend fun changeFavoriteStatusInArticleByUrl(key: String) {
        newsLocalDataSource.changeFavoriteStatusInArticleByUrl(key)
    }
}
