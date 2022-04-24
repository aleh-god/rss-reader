package by.godevelopment.alfarssreader.ui.newscard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CardsAdapter(
    activity: FragmentActivity,
    private var dataList: List<String>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = dataList.size

    override fun createFragment(position: Int): Fragment {
        val dataItem = dataList[position]
        return ItemCardsFragment(dataItem)
    }
}