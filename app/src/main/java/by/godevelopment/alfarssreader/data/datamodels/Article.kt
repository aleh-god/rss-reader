package by.godevelopment.alfarssreader.data.datamodels

import androidx.room.Embedded
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "article", primaryKeys = ["title"])
data class Article(
    @Json(name = "author")
    val author: String? = null,
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "publishedAt")
    val publishedAt: String? = null,
    @Embedded
    @Json(name = "source")
    val source: Source,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "urlToImage")
    val urlToImage: String? = null
)