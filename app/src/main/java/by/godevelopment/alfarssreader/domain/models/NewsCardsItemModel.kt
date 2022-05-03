package by.godevelopment.alfarssreader.domain.models

data class NewsCardsItemModel(
    val isFavorite: Boolean = false,
    val storageUri: String? = null,
    val url: String
)
