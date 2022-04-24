package by.godevelopment.alfarssreader.ui.newscard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import by.godevelopment.alfarssreader.MainActivity
import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.databinding.FragmentNewsCardBinding
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsCardFragment : Fragment() {

    companion object {
        fun newInstance() = NewsCardFragment()
    }

    private val viewModel: NewsCardViewModel by viewModels()
    private var _binding: FragmentNewsCardBinding? = null
    private val  binding: FragmentNewsCardBinding get() = _binding!!
    private val args: NewsCardFragmentArgs by navArgs()
    private val pagerCallBack by lazy {
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val t = requireActivity() as MainActivity
                val toolbar = t.findViewById<MaterialToolbar>(R.id.toolbar)
                toolbar.title = viewModel.urlList[position]
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsCardBinding.inflate(inflater, container, false)
        setupUi()
        return binding.root
    }

    private fun setupUi() {
        val cardsAdapter = CardsAdapter(requireActivity(), viewModel.urlList)
        val position = args.idItem
        binding.cardsPager.apply {
            adapter = cardsAdapter
            setPageTransformer(ZoomOutPageTransformer())
            registerOnPageChangeCallback(pagerCallBack)
            post {
                setCurrentItem(position, true)
            }
        }
    }

    override fun onDestroy() {
        binding.cardsPager.unregisterOnPageChangeCallback(pagerCallBack)
        _binding = null
        super.onDestroy()
    }
}