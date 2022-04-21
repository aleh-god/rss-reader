package by.godevelopment.alfarssreader.domain.repositories

import by.godevelopment.alfarssreader.data.datamodels.RssModel

interface NewsRepository {

    suspend fun fetchAllNews(): RssModel
}