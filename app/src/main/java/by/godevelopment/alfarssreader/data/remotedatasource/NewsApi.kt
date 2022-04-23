package by.godevelopment.alfarssreader.data.remotedatasource

import by.godevelopment.alfarssreader.data.datamodels.NewsApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines?country=us&category=business")
    suspend fun fetchNewsModelWithKey(@Query("apiKey") key: String): NewsApiModel
}