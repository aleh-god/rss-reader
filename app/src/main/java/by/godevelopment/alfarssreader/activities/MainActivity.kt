package by.godevelopment.alfarssreader.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.commons.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "MainActivity onCreate")
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        Log.i(TAG, "MainActivity onCreate setContentView")
//        lifecycleScope.launchWhenStarted {
//            Log.i(TAG, "MainActivity .launchWhenCreated")
//            viewModel.uiState.collect { state ->
//                Log.i(TAG, "MainActivity .uiState.collect $state")
//                installSplashScreen().apply {
//                    setKeepOnScreenCondition {
//                        state
//                    }
//                    Log.i(TAG, "MainActivity .installSplashScreen()")
//                }
//            }
//        }
        setContentView(R.layout.activity_main)
    }
}