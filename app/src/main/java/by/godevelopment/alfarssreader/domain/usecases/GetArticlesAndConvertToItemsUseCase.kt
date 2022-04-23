package by.godevelopment.alfarssreader.domain.usecases

import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.domain.helpers.StringHelper
import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import by.godevelopment.alfarssreader.ui.newslist.NewsItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetArticlesAndConvertToItemsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val stringHelper: StringHelper
) {
    operator fun invoke(): Flow<List<NewsItemModel>> =
        newsRepository
            .getArticlesAsFlow()
            .map { list ->
                list.map {
                    NewsItemModel(
                        textTitle = it.title
                            ?: stringHelper.getString(R.string.article_title_null_stub),
                        textAuthor = it.author
                            ?: stringHelper.getString(R.string.article_author_null_stub),
                        textSourceName = it.source.name
                            ?: stringHelper.getString(R.string.article_source_null_stub),
                        textDescription = it.description
                            ?: stringHelper.getString(R.string.article_description_null_stub),
                        textPublishedAt = it.publishedAt
                            ?: stringHelper.getString(R.string.article_time_null_stub),
                        urlToImage = it.urlToImage,
                        url = it.url
                    )
                }
            }
}