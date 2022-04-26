package by.godevelopment.alfarssreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.godevelopment.alfarssreader.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private val navController by lazy {
//        findNavController(R.id.nav_host_fragment)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}