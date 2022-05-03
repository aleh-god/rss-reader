package by.godevelopment.alfarssreader.di

import android.content.Context
import androidx.room.Room
import by.godevelopment.alfarssreader.data.localdatasource.NewsDao
import by.godevelopment.alfarssreader.data.localdatasource.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.getNewsDao()

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, "news_database.db").build()
}
