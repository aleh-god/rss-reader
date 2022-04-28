package by.godevelopment.alfarssreader.domain.usecases

import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import by.godevelopment.alfarssreader.ui.models.NewsItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteArticlesAndConvertToItemsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val convertDateToStringUseCase: ConvertDateToStringUseCase
) {
    operator fun invoke(): Flow<List<NewsItemModel>> =
        newsRepository
            .getArticlesAsFlow()
            .map { list ->
                list
                    .filter {
                        it.isFavorite
                    }
                    .map {
                        NewsItemModel(
                            isFavorite = it.isFavorite,
                            textTitle = it.title,
                            textAuthor = it.author,
                            textSourceName = it.name,
                            textDescription = it.description,
                            textPublishedAt = convertDateToStringUseCase(it.publishedAt),
                            urlToImage = it.urlToImage,
                            url = it.url
                        )
                    }
            }
}