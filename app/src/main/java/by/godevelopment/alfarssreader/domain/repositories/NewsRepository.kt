package by.godevelopment.alfarssreader.domain.repositories

import by.godevelopment.alfarssreader.data.datamodels.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getArticlesAsFlow(): Flow<List<Article>>
}