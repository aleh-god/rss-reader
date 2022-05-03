package by.godevelopment.alfarssreader.workers

import android.util.Log
import androidx.work.*
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.commons.TIME_INTERVAL_MIN
import by.godevelopment.alfarssreader.commons.WORK_NAME
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsReloadTaskScheduler @Inject constructor(
    private val workManager: WorkManager
) {
    fun startNewsReloadTask() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val repeatingRequest =
            PeriodicWorkRequestBuilder<NewsReloadWorker>(TIME_INTERVAL_MIN, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )

        Log.i(TAG, "NewsReaderApp setupNewsReloadWork: $constraints $repeatingRequest")
    }
}
