package by.godevelopment.alfarssreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.domain.repositories.NewsRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenStarted {
            val result = repository.fetchAllNews()
            Log.i(TAG, "onCreate: ${result.channel.title}")
            val textView = findViewById<TextView>(R.id.message)
            textView.text = result.channel.description
        }
    }
}