package by.godevelopment.alfarssreader.domain.usecases

import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import javax.inject.Inject

class ChangeFavoriteStatusInNewsCardUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(key: String) {
        newsRepository.changeFavoriteStatusInArticleByUrl(key)
    }
}
