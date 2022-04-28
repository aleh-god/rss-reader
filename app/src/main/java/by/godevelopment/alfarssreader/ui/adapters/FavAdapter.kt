package by.godevelopment.alfarssreader.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.godevelopment.alfarssreader.databinding.ItemFavListBinding
import by.godevelopment.alfarssreader.ui.models.NewsItemModel

class FavAdapter(
    private val onClickRead: (String) -> Unit,
    private val onClickDel: (String) -> Unit
) : RecyclerView.Adapter<FavAdapter.ItemViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<NewsItemModel>() {
        override fun areItemsTheSame(oldItem: NewsItemModel, newItem: NewsItemModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsItemModel, newItem: NewsItemModel): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var itemList: List<NewsItemModel>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    inner class ItemViewHolder(private val binding: ItemFavListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItemModel: NewsItemModel) {
            binding.apply {
                textTitle.text = newsItemModel.textTitle
                textAuthor.text = newsItemModel.textAuthor

                buttonDelNews.setOnClickListener {
                    onClickDel.invoke(newsItemModel.url)
                }
                buttonReadNews.setOnClickListener {
                    onClickRead.invoke(newsItemModel.url)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemFavListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}