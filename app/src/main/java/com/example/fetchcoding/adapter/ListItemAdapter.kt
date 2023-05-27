package com.example.fetchcoding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchcoding.databinding.ListItemBinding
import com.example.fetchcoding.model.ListItem

class ListItemAdapter() : RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val filteredListItems = listItems.filter { !it.name.isNullOrBlank() }

        val sortedlistItems = filteredListItems.sortedWith (compareBy({ it.listId }, { it.name }))

        val items = sortedlistItems[position]

            holder.binding.apply {
                tvID.text = items.id.toString()
                tvListID.text = items.listId.toString()
                tvName.text = items.name
            }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    var listItems: List<ListItem>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    class ListItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}