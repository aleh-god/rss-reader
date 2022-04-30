package by.godevelopment.alfarssreader

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.commons.TIME_INTERVAL_MIN
import by.godevelopment.alfarssreader.commons.WORK_NAME
import by.godevelopment.alfarssreader.di.DefaultDispatcher
import by.godevelopment.alfarssreader.workers.NewsReloadWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class NewsReaderApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

//    @Inject
//    @DefaultDispatcher
//    lateinit var dispatcher : CoroutineDispatcher
//    private val applicationScope = CoroutineScope(dispatcher)

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            Log.i(TAG, "NewsReaderApp delayedInit: .launch")
            setupNewsReloadWork()
        }
    }

    private fun setupNewsReloadWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val repeatingRequest =
            PeriodicWorkRequestBuilder<NewsReloadWorker>(TIME_INTERVAL_MIN, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)

        Log.i(TAG, "NewsReaderApp setupNewsReloadWork: $constraints $repeatingRequest")
    }
}