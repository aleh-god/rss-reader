package by.godevelopment.alfarssreader.ui.models

data class NewsItemModel(
    val isFavorite: Boolean = false,
    val textTitle: String,
    val textAuthor: String,
    val textSourceName: String,
    val textDescription: String,
    val textPublishedAt: String,
    val urlToImage: String? = null,
    val url: String,
)
