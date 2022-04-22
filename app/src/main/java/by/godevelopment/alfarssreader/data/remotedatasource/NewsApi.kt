package by.godevelopment.alfarssreader.data.remotedatasource

import by.godevelopment.alfarssreader.data.datamodels.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines?country=us&category=business")
    suspend fun fetchAllNews(@Query("apiKey") key: String): NewsModel
}