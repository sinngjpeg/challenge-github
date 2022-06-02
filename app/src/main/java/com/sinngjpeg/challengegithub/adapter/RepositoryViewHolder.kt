package com.sinngjpeg.challengegithub.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinngjpeg.challengegithub.R
import com.sinngjpeg.challengegithub.databinding.ItemRepositoryBinding
import com.sinngjpeg.challengegithub.model.Item

class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val repositoryName = binding.txtRepositoryName
    private val repositoryDescription = binding.txtRepositoryDescription
    private val repositoryAuthor = binding.txtRepositoryAuthor
    private val avatar = binding.ivUser
    private val forks = binding.txtForks
    private val stars = binding.txtStars

    fun bind(item: Item) = with(binding) {
        repositoryName.text = item.name
        repositoryDescription.text = item.description
        repositoryAuthor.text = item.owner.login
        forks.text = item.forks_count.toString()
        stars.text = item.stargazers_count.toString()
        Glide.with(itemView.context).load(item.owner.avatar_url).fallback(R.drawable.github)
            .circleCrop().into(avatar)
    }
}