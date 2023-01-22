package by.godevelopment.alfarssreader.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.godevelopment.alfarssreader.R
import by.godevelopment.alfarssreader.databinding.ItemFavListBinding
import by.godevelopment.alfarssreader.domain.models.NewsItemModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

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

        private var itemUrl: String? = null

        init {
            binding.apply {
                buttonDelNews.setOnClickListener { _ ->
                    itemUrl?.let { onClickDel(it) }
                }
                buttonReadNews.setOnClickListener { _ ->
                    itemUrl?.let { onClickRead(it) }
                }
            }
        }

        fun bind(newsItemModel: NewsItemModel) {
            itemUrl = newsItemModel.url
            binding.apply {
                textTitle.text = newsItemModel.textTitle
                textAuthor.text = newsItemModel.textAuthor
                newsItemModel.urlToImage?.let {
                    Glide.with(root)
                        .load(it)
                        .centerCrop() // .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .error(R.drawable.image_not_loaded)
                        .placeholder(R.drawable.image)
                        .into(image)
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
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}
