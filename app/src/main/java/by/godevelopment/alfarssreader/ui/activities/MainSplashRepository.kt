package by.godevelopment.alfarssreader.ui.activities

import kotlinx.coroutines.flow.Flow

interface MainSplashRepository {

    suspend fun reloadNewsFromRemoteToLocalDataSource()

    fun getDbIsReadyStateAsFlow(): Flow<Boolean>
}