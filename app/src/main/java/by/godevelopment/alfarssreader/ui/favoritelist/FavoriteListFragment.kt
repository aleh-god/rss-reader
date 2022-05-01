package by.godevelopment.alfarssreader.ui.favoritelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.databinding.FragmentFavoriteListBinding
import by.godevelopment.alfarssreader.ui.adapters.FavAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FavoriteListFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteListFragment()
    }

    private val viewModel: FavoriteListViewModel by viewModels()
    private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!

    private val onClickRead: (String) -> Unit = {
        Log.i(TAG, "onClickRead: Navigate to $it")
        val action = FavoriteListFragmentDirections.actionFavoriteListFragmentToNewsCardFragment(it)
        findNavController().navigate(action)
    }
    private val onClickDel: (String) -> Unit = {
        Log.i(TAG, "onClickAdd: changeFavoriteStatusInNewsCard $it")
        viewModel.changeFavoriteStatusInNewsCard(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        setupUi()
        setupEvent()
        setHasOptionsMenu(true)
        setupListener()
        return binding.root
    }

    private fun setupListener() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.news_list -> {
                    Log.i(TAG, "toolbar.setOnMenuItemClickListener: news_list")
                    findNavController().navigate(R.id.action_favoriteListFragment_to_newsListFragment)
                    true
                }
                else -> false
            }
        }
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
    }

    private fun setupUi() {
        val rvAdapter = FavAdapter(onClickRead, onClickDel)
        binding.apply {
            recyclerView.adapter = rvAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                Log.i(TAG, "FavoriteListFragment setupUi: ${uiState.dataList.size}")
                if (!uiState.isFetchingData) {
                    binding.linearProgress.visibility = View.GONE
                } else binding.linearProgress.visibility = View.VISIBLE
                if (!uiState.dataList.isNullOrEmpty()) {
                    Log.i(TAG, "FavoriteListFragment rvAdapter.itemList: ${uiState.dataList.size}")
                    rvAdapter.itemList = uiState.dataList
                }
                binding.toolbar.apply {
                    subtitle = context
                        .getString(R.string.toolbar_news_subtitle_text) + uiState.dataList.size
                    title = context.getString(R.string.label_fav_list)
                }
            }
        }
    }

    private fun setupEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect {
                Snackbar
                    .make(binding.root, it, Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.snackbar_btn_reload))
                    { viewModel.fetchDataModel() }
                    .show()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}