package by.godevelopment.alfarssreader.data.remotedatasource

import by.godevelopment.alfarssreader.data.datamodels.Article
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val newsApi: NewsApi
) {
    suspend fun fetchLatestArticles(): List<Article> =
        newsApi
            .fetchNewsModelWithKey(KEY_API)
            .articles
}