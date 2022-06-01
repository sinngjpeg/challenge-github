package com.sinngjpeg.challengegithub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinngjpeg.challengegithub.R
import com.sinngjpeg.challengegithub.databinding.ItemRepositoryBinding
import com.sinngjpeg.challengegithub.model.Item

class RepositoryAdapter(private val onClick: (Item) -> Unit) :
    PagingDataAdapter<Item, RepositoryAdapter.RepositoryListViewHolder>(
        RepositoryItemDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryListViewHolder {
        return RepositoryListViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }


    inner class RepositoryListViewHolder(itemRepositoryBinding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(itemRepositoryBinding.root) {

        private val username: TextView = itemRepositoryBinding.txtRepositoryUsername
        private val starts: TextView = itemRepositoryBinding.txtStars
        private val forks: TextView = itemRepositoryBinding.txtForks
        private val repositoryName: TextView = itemRepositoryBinding.txtRepositoryName
        private val fullName: TextView = itemRepositoryBinding.txtRepositoryName
        private val description: TextView = itemRepositoryBinding.txtRepositoryDescription
        private val ivAvatar: ImageView = itemRepositoryBinding.ivUser
        private val cardView: CardView = itemRepositoryBinding.cardRepository


        fun bind(item: Item) {
            username.text = item.owner.login
            starts.text = item.stargazers_count.toString()
            forks.text = item.forks.toString()
            repositoryName.text = item.name
            fullName.text = item.full_name
            description.text = item.description
            Glide.with(ivAvatar)
                .load(item.owner.avatar_url)
                .circleCrop()
                .into(ivAvatar)
            cardView.setOnClickListener {
                onClick(item)
            }
        }
    }
}


class RepositoryItemDiffCallback :
    DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

}
