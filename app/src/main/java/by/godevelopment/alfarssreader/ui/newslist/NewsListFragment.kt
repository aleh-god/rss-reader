package by.godevelopment.alfarssreader.ui.newslist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.databinding.FragmentNewsListBinding
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
    private val onClick: (Int) -> Unit = {
            Log.i(TAG, "onClick: Navigate to $it")
            val action = NewsListFragmentDirections.actionNewsListFragmentToNewsCardFragment(it)
            findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        setupUi()
        setupEvent()
        return binding.root
    }

    private fun setupUi() {
        val rvAdapter = NewsAdapter(onClick)
        binding.apply {
            recyclerView.adapter = rvAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            swipeContainer.apply {
                setOnRefreshListener {
                    viewModel.fetchDataModel()
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
                Log.i(TAG, "setupUi: $uiState")
                if (!uiState.isFetchingData) {
                    binding.linearProgress.visibility = View.GONE
                    binding.swipeContainer.isRefreshing = false
                } else binding.linearProgress.visibility = View.VISIBLE
                rvAdapter.itemList = uiState.dataList
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