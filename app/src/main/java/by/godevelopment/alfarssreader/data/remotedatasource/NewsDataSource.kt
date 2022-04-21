package by.godevelopment.alfarssreader.data.remotedatasource

import by.godevelopment.alfarssreader.data.datamodels.RssModel
import javax.inject.Inject

class NewsDataSource @Inject constructor(
    private val newsApi: NewsApi
) {
    suspend fun fetchAllNews(): RssModel = newsApi.fetchAllNews()
}