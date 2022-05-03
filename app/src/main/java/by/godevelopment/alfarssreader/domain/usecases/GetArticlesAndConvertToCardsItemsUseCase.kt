package by.godevelopment.alfarssreader.domain.usecases

import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import by.godevelopment.alfarssreader.domain.models.NewsCardsItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetArticlesAndConvertToCardsItemsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<NewsCardsItemModel>> =
        newsRepository
            .getArticlesAsFlow()
            .map { list ->
                list.map {
                    NewsCardsItemModel(
                        isFavorite = it.isFavorite,
                        storageUri = it.storageUri,
                        url = it.url
                    )
                }
            }
}
