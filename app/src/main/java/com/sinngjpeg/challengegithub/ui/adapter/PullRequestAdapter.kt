package com.sinngjpeg.challengegithub.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinngjpeg.challengegithub.databinding.ItemPullRequestBinding
import com.sinngjpeg.challengegithub.model.PullRequest
import com.sinngjpeg.challengegithub.ui.fragment.PullRequestFragmentDirections

class PullRequestAdapter :
    RecyclerView.Adapter<PullRequestAdapter.ListPullRequestViewHolder>() {

    inner class ListPullRequestViewHolder(val itemPullRequestBinding: ItemPullRequestBinding) :
        RecyclerView.ViewHolder(itemPullRequestBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<PullRequest>() {
        override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var pullRequest: List<PullRequest>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPullRequestViewHolder {
        return ListPullRequestViewHolder(
            ItemPullRequestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = pullRequest.size

    override fun onBindViewHolder(holder: ListPullRequestViewHolder, position: Int) {
        val pullRequest = pullRequest[position]
        holder.itemPullRequestBinding.apply {
            txtPullRequestTitle.text = pullRequest.title
            txtPullRequestDescription.text = pullRequest.body
            txtPullRequestUsername.text = pullRequest.user.login
            txtPullRequestFullName.text = pullRequest.user.login + pullRequest.title
            Glide.with(ivUser)
                .load(pullRequest.user.avatar_url)
                .circleCrop()
                .into(ivUser)
            cardPullRequest.setOnClickListener { view ->
                val action =
                    PullRequestFragmentDirections.actionPullRequestFragmentToWebViewFragment(
                        pullRequest.html_url,
                        pullRequest.title
                    )
                view.findNavController().navigate(action)
            }

        }
    }
}