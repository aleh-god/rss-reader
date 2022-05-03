package by.godevelopment.alfarssreader.ui.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.databinding.FragmentNewsListBinding
import by.godevelopment.alfarssreader.ui.adapters.NewsAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    companion object {
        fun newInstance() = NewsListFragment()
    }

    private val viewModel: NewsListViewModel by viewModels()
    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!

    private val onClickRead: (String) -> Unit = {
        findNavController().navigate(
            NewsListFragmentDirections.actionNewsListFragmentToNewsCardFragment(it)
        )
    }
    private val onClickAdd: (String) -> Unit = {
        viewModel.changeFavoriteStatusInNewsCard(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        setupUi()
        setupEvent()
        setHasOptionsMenu(true)
        setupListener()

        return binding.root
    }

    private fun setupListener() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.favorite_list -> {
                    findNavController().navigate(R.id.action_newsListFragment_to_favoriteListFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupUi() {
        val rvAdapter = NewsAdapter(onClickRead, onClickAdd)
        binding.apply {
            recyclerView.adapter = rvAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            swipeContainer.apply {
                setOnRefreshListener {
                    viewModel.reloadDataList()
                }
                setColorSchemeResources(
                    android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                if (!uiState.isFetchingData) {
                    binding.linearProgress.visibility = View.GONE
                    binding.swipeContainer.isRefreshing = false
                } else binding.linearProgress.visibility = View.VISIBLE
                if (!uiState.dataList.isNullOrEmpty()) {
                    rvAdapter.itemList = uiState.dataList
                }
                binding.toolbar.apply {
                    subtitle = context
                        .getString(R.string.toolbar_news_subtitle_text) + uiState.dataList.size
                    title = context.getString(R.string.label_news_list)
                }
            }
        }
    }

    private fun setupEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect {
                Snackbar
                    .make(binding.root, it, Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.snackbar_btn_reload)) {
                        viewModel.reloadDataList()
                    }
                    .show()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
