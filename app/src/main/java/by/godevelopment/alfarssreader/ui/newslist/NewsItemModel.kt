package by.godevelopment.alfarssreader.ui.newslist

data class NewsItemModel(
    val textTitle: String,
    val textAuthor: String,
    val textSourceName: String,
    val textDescription: String,
    val textPublishedAt: String,
    val urlToImage: String? = null,
    val url: String? = null,
)
