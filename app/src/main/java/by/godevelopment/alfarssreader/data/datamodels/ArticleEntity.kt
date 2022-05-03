package by.godevelopment.alfarssreader.data.datamodels

import androidx.room.Entity

@Entity(tableName = "article", primaryKeys = ["url"])
data class ArticleEntity(
    val isFavorite: Boolean = false,
    val storageUri: String? = null,
    val author: String,
    val description: String,
    val publishedAt: Long,
    val id: String,
    val name: String,
    val title: String,
    val url: String,
    val urlToImage: String? = null,
    val content: String
)
