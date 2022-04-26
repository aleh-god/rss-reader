package by.godevelopment.alfarssreader.domain.repositories

import by.godevelopment.alfarssreader.data.datamodels.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getArticlesAsFlow(): Flow<List<ArticleEntity>>

    suspend fun setFavoriteStatusInArticleByUrl(key: String): Boolean

    suspend fun changeFavoriteStatusInArticleByUrl(key: String)
}