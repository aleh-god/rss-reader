package by.godevelopment.alfarssreader.ui.newscard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.databinding.ItemCardsFragmentBinding

class ItemCardsFragment : Fragment() {

    private var _binding: ItemCardsFragmentBinding? = null
    private val binding: ItemCardsFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemCardsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            binding.webView.restoreState(savedInstanceState)
        } else {
            arguments?.takeIf { it.containsKey(TAG) }?.apply {
                binding.webView.webViewClient = WebViewClient()
                binding.webView.webChromeClient = WebChromeClient()
                binding.webView.loadUrl(getString(TAG).toString())
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
