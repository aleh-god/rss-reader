package by.godevelopment.alfarssreader.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.data.repositories.NewsRepositoryImpl
import by.godevelopment.alfarssreader.di.IoDispatcher
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@HiltWorker
class NewsReloadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val newsRepositoryImpl: NewsRepositoryImpl,
    @IoDispatcher private val workDispatcherIO: CoroutineDispatcher
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return withContext(workDispatcherIO) {
            try {
                newsRepositoryImpl.reloadNewsFromRemoteToLocalDataSource()
            } catch (e: Exception) {
                Log.i(TAG, "NewsReloadWorker.catch: ${e.message}")
                Result.failure()
            }
            Result.success()
        }
    }
}
