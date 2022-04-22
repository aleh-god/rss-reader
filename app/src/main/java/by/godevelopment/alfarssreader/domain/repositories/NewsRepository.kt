package by.godevelopment.alfarssreader.domain.repositories

import by.godevelopment.alfarssreader.data.datamodels.NewsModel

interface NewsRepository {

    suspend fun fetchAllNews(): NewsModel
}