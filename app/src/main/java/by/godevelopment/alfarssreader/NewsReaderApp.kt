package by.godevelopment.alfarssreader

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.workers.NewsReloadTaskScheduler
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class NewsReaderApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var applicationScope: CoroutineScope

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    @Inject
    fun startNewsReloadTask(newsReloadTaskScheduler: NewsReloadTaskScheduler) {
        applicationScope.launch {
            Log.i(TAG, "NewsReaderApp delayedInit: .launch")
            newsReloadTaskScheduler.startNewsReloadTask()
        }
    }
}
