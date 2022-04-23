package by.godevelopment.alfarssreader.data.repositories

import by.godevelopment.alfarssreader.data.datamodels.Article
import by.godevelopment.alfarssreader.data.localdatasource.NewsLocalDataSource
import by.godevelopment.alfarssreader.data.remotedatasource.NewsRemoteDataSource
import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override fun getArticlesAsFlow(): Flow<List<Article>> =
        newsLocalDataSource
            .getAllNews()
            .map {
                it.ifEmpty { newsRemoteDataSource.fetchLatestArticles() }
            }
}