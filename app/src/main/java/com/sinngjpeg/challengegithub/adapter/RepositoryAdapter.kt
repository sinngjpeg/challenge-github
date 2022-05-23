package com.sinngjpeg.challengegithub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinngjpeg.challengegithub.R
import com.sinngjpeg.challengegithub.model.Item
import com.sinngjpeg.challengegithub.viewmodel.RepositoryViewHolder

class RepositoryAdapter(private val repository: List<Item>) :
    RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repository.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        return holder.bind(repository[position])
    }
}
