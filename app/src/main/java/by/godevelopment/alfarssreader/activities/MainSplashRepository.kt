package by.godevelopment.alfarssreader.activities

import kotlinx.coroutines.flow.Flow

interface MainSplashRepository {

    suspend fun reloadNewsFromRemoteToLocalDataSource()

    fun getDbIsReadyStateAsFlow(): Flow<Boolean>
}