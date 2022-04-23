package by.godevelopment.alfarssreader.data.datamodels

import by.godevelopment.alfarssreader.commons.INIT_STATUS
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsApiModel(
    @Json(name = "articles")
    val articles: List<Article> = emptyList(),
    @Json(name = "status")
    val status: String = INIT_STATUS,
    @Json(name = "totalResults")
    val totalResults: Int = 0
)