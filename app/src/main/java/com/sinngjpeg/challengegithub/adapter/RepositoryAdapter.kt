package com.sinngjpeg.challengegithub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinngjpeg.challengegithub.databinding.ItemRepositoryBinding
import com.sinngjpeg.challengegithub.model.Item

class RepositoryAdapter(private val repository: List<Item>) :
    RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RepositoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repository.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository: Item = repository[position]
        holder.bind(repository)
    }
}