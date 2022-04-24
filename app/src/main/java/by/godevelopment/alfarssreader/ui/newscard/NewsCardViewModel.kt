package by.godevelopment.alfarssreader.ui.newscard

import androidx.lifecycle.ViewModel
import by.godevelopment.alfarssreader.domain.usecases.GetNewsCardByUrlUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsCardViewModel @Inject constructor(
    private val getNewsCardByUrlUseCase: GetNewsCardByUrlUseCase
) : ViewModel() {

    val urlList: List<String> = listOf(
        "https://www.businessinsider.com/elon-musk-buying-twitter-doesnt-care-economics-trusted-public-platform-2022-4",
        "https://www.reuters.com/world/europe/amsterdam-airport-urges-travellers-stay-away-strike-causes-chaos-2022-04-23/",
        "https://www.cnbc.com/2022/04/23/real-estate-investors-side-hustle-mining-110000-in-bitcoin-a-month.html",
        "www.cnbc.com/2022/04/23/how-netflixs-password-sharing-crackdown-is-likely-to-work.html",
        "https://www.reuters.com/world/europe/amsterdam-airport-urges-travellers-stay-away-strike-causes-chaos-2022-04-23/",
        "https://www.cnbc.com/2022/04/23/real-estate-investors-side-hustle-mining-110000-in-bitcoin-a-month.html",
        "www.cnbc.com/2022/04/23/how-netflixs-password-sharing-crackdown-is-likely-to-work.html",
        "https://www.businessinsider.com/elon-musk-buying-twitter-doesnt-care-economics-trusted-public-platform-2022-4"
    )
}