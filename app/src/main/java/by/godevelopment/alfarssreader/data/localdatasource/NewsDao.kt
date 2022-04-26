package by.godevelopment.alfarssreader.data.localdatasource

import androidx.room.*
import by.godevelopment.alfarssreader.data.datamodels.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllArticleEntities(vararg articles: ArticleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replaceAllArticleEntities(vararg articles: ArticleEntity)

    @Delete
    suspend fun deleteArticleEntity(article: ArticleEntity)

    @Query("SELECT * FROM article ORDER BY publishedAt DESC")
    fun getAllArticleEntities(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM article WHERE url = :key")
    suspend fun getArticleEntityByUrl(key: String): ArticleEntity
}