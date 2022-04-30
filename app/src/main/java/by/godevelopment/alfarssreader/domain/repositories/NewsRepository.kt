package by.godevelopment.alfarssreader.domain.repositories

import by.godevelopment.alfarssreader.data.datamodels.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getArticlesAsFlow(): Flow<List<ArticleEntity>>

    suspend fun changeFavoriteStatusInArticleByUrl(key: String)

    suspend fun reloadNewsFromRemoteToLocalDataSource()
}
