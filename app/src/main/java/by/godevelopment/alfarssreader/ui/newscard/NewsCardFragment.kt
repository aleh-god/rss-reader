package by.godevelopment.alfarssreader.ui.newscard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.databinding.FragmentNewsCardBinding
import by.godevelopment.alfarssreader.ui.adapters.CardsAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class NewsCardFragment : Fragment() {

    companion object {
        fun newInstance() = NewsCardFragment()
    }

    private val viewModel: NewsCardViewModel by viewModels()
    private var _binding: FragmentNewsCardBinding? = null
    private val  binding: FragmentNewsCardBinding get() = _binding!!
    private val args: NewsCardFragmentArgs by navArgs()

    private var viewPagerCallBack: ViewPager2.OnPageChangeCallback? = null
    private var currentCardUrlInViewPager: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsCardBinding.inflate(inflater, container, false)
        setupUi()
        setupEvent()
        setupListener()
        return binding.root
    }

    private fun setupUi() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                Log.i(TAG, "NewsCardFragment setupUi: ${uiState.dataList.size}")

                if (!uiState.isFetchingData) {
                    binding.linearProgress.visibility = View.GONE
                } else binding.linearProgress.visibility = View.VISIBLE

                if (!uiState.dataList.isNullOrEmpty()) {

                    val cardsAdapter = CardsAdapter(requireActivity(), uiState.dataList)
                    if (viewModel.currentCardPosition == null) {
                        viewModel.currentCardPosition = uiState
                            .dataList
                            .indexOfFirst { args.url == it.url }
                    }
                    Log.i(TAG, "NewsCardFragment: position ${viewModel.currentCardPosition} = ${args.url}")

                    binding.cardsPager.apply {
                        adapter = cardsAdapter
                        setPageTransformer(ZoomOutPageTransformer())
                        viewModel.currentCardPosition?.let {
                            post { setCurrentItem(it, true) }
                        }
                        viewPagerCallBack = object : ViewPager2.OnPageChangeCallback() {

                            override fun onPageSelected(position: Int) {
                                currentCardUrlInViewPager = uiState.dataList[position].url
                                viewModel.currentCardPosition = position
                                binding.toolbar.apply {
                                    subtitle = currentCardUrlInViewPager
                                    title = context
                                        .getString(R.string.toolbar_news_card_tittle) + (position + 1)
                                    val item = menu.findItem(R.id.add_favorite)
                                    if (uiState.dataList[position].isFavorite)
                                        item.setIcon(R.drawable.ic_baseline_favorite_24)
                                    else item.setIcon(R.drawable.ic_baseline_favorite_border_24)
                                }
                                Log.i(TAG, "onPageSelected: Position $position, fav ${uiState.dataList[position].isFavorite}")
                            }
                        }
                        viewPagerCallBack?.let {
                            registerOnPageChangeCallback(it)
                        }
                    }
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

    private fun setupListener() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add_favorite -> {
                    Log.i(TAG, "toolbar.setOnMenuItemClickListener: favorite_list")
                    viewModel.changeFavoriteStatusInNewsCard(currentCardUrlInViewPager)
                    true
                }
                else -> false
            }
        }
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        viewPagerCallBack?.let {
            binding.cardsPager.unregisterOnPageChangeCallback(it)
        }
        _binding = null
        super.onDestroy()
    }
}