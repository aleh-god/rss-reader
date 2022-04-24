package by.godevelopment.alfarssreader

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        setupToolbar(menu)
        return true
    }

    private fun setupToolbar(menu: Menu) {
        val itemList = menu.findItem(R.id.favorite_list)
        val itemAdd = menu.findItem(R.id.add_favorite)
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.newsListFragment -> {
                    itemList.isVisible = true
                    itemAdd.isVisible = false
                }
                R.id.newsCardFragment -> {
                    itemList.isVisible = false
                    itemAdd.isVisible = true
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.favorite_list -> {
            Log.i(TAG, "onOptionsItemSelected: R.id.menu_settings")
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}