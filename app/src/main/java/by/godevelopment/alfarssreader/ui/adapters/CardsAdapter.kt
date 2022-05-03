package by.godevelopment.alfarssreader.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.ui.newscard.ItemCardsFragment
import by.godevelopment.alfarssreader.domain.models.NewsCardsItemModel

class CardsAdapter(
    activity: FragmentActivity,
    private var dataList: List<NewsCardsItemModel>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = dataList.size

    override fun createFragment(position: Int): Fragment {
        val dataItem = dataList[position]
        val fragment = ItemCardsFragment()
        fragment.arguments = Bundle().apply {
            putString(TAG, dataItem.url)
        }
        return fragment
    }
}
