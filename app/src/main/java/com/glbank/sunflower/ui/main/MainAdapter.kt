package com.glbank.sunflower.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.glbank.sunflower.data.model.Task
import com.glbank.sunflower.databinding.ItemTaskBinding

class MainAdapter(private val listener: (Task) -> Unit) :
    PagingDataAdapter<Task, MainAdapter.TaskViewHolder>(DiffUtilTask()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindItem(it, listener) }
    }

    class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(task: Task, listener: (Task) -> Unit) {
            binding.apply {
                itemTitle.text = task.title
                itemDesc.text = task.des
                root.setOnClickListener {
                    listener(task)
                }
            }
        }
    }

    private class DiffUtilTask : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return newItem == oldItem
        }
    }
}