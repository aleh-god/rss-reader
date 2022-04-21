package by.godevelopment.alfarssreader.data.remotedatasource

import by.godevelopment.alfarssreader.data.datamodels.RssModel
import retrofit2.http.GET

interface NewsApi {

    // https://alfabank.ru/_/rss/_rss.html?subtype=1&category=2&city=21
    @GET("_rss.html?subtype=1&category=2&city=21")
    suspend fun fetchAllNews(): RssModel
}