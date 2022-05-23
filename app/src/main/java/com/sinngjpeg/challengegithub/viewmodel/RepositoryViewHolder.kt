package com.sinngjpeg.challengegithub.viewmodel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinngjpeg.challengegithub.R
import com.sinngjpeg.challengegithub.model.Item

class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val repositoryName: TextView = itemView.findViewById(R.id.txt_repository_name)
    private val repositoryDescription: TextView =
        itemView.findViewById(R.id.txt_repository_description)
    private val repositoryAuthor: TextView = itemView.findViewById(R.id.txt_repository_author)
    private val avatar: ImageView = itemView.findViewById(R.id.iv_user)
    private val forks: TextView = itemView.findViewById(R.id.txt_forks)
    private val stars: TextView = itemView.findViewById(R.id.txt_stars)

    fun bind(item: Item) {
        repositoryName.text = item.name
        repositoryDescription.text = item.description
        repositoryAuthor.text = item.owner.login
        forks.text = item.forks_count.toString()
        stars.text = item.stargazers_count.toString()
        Glide.with(itemView.context).load(item.owner.avatar_url).circleCrop().into(avatar)
    }
}
