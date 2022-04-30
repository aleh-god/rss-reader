package by.godevelopment.alfarssreader.domain.usecases

import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import javax.inject.Inject

class ReloadDataUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke() {
        newsRepository.reloadNewsFromRemoteToLocalDataSource()
    }
}