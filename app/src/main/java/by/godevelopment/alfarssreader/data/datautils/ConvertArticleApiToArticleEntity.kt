package by.godevelopment.alfarssreader.data.datautils

import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.commons.INT_STUB
import by.godevelopment.alfarssreader.commons.LONG_STUB
import by.godevelopment.alfarssreader.data.datamodels.Article
import by.godevelopment.alfarssreader.data.datamodels.ArticleEntity
import by.godevelopment.alfarssreader.domain.helpers.StringHelper
import javax.inject.Inject

class ConvertArticleApiToArticleEntity @Inject constructor(
    private val stringHelper: StringHelper,
    private val convertFromISO8601UTC: ConvertFromISO8601UTC
) {
    operator fun invoke(article: Article): ArticleEntity {
        stringHelper.apply {
            article.apply {
                return ArticleEntity(
                    author = author ?: getString(R.string.article_author_null_stub),
                    description = description ?: getString(R.string.article_description_null_stub),
                    publishedAt = convertFromISO8601UTC(publishedAt) ?: LONG_STUB,
                    id = source.id ?: INT_STUB,
                    name = source.name ?: getString(R.string.name_title_null_stub),
                    title = title ?: getString(R.string.article_title_null_stub),
                    url = url,
                    urlToImage = urlToImage,
                    content = content ?: getString(R.string.content_description_null_stub)
                )
            }
        }
    }
}
