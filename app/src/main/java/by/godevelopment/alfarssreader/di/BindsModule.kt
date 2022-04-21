package by.godevelopment.alfarssreader.di

import by.godevelopment.alfarssreader.data.repositories.NewsRepositoryImpl
import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    abstract fun bindRepositoryToImpl(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository
}