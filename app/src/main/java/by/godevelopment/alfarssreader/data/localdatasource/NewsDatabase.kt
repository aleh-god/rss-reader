package by.godevelopment.alfarssreader.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import by.godevelopment.alfarssreader.data.datamodels.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao
}