package by.godevelopment.alfarssreader.ui.newslist

data class NewsItemModel(
    val id: Int,
    val textTitle: String,
    val textAuthor: String,
    val textSourceName: String,
    val textDescription: String,
    val textPublishedAt: Long,
    val urlToImage: String? = null,
    val url: String? = null,
)
