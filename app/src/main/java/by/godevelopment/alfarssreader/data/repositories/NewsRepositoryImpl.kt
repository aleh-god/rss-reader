package by.godevelopment.alfarssreader.data.repositories

import by.godevelopment.alfarssreader.data.remotedatasource.NewsDataSource
import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDataSource: NewsDataSource
) : NewsRepository {

    override suspend fun fetchAllNews() = newsDataSource.fetchAllNews()
}