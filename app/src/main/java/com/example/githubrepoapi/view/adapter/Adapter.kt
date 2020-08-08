package com.example.githubrepoapi.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepoapi.R
import com.example.githubrepoapi.service.model.Project
import kotlinx.android.synthetic.main.item.view.*

class Adapter(private val listener: OnItemClickListener) :
    ListAdapter<Project, Adapter.ViewHolder>(NewsFeedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProject = currentList

        val languages = "Languages: " + currentProject[position].languages
        val watchers = "Watchers: " + currentProject[position].watchers

        holder.name.text = currentProject[position].name
        holder.languages.text = languages
        holder.watchers.text = watchers
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val name: TextView = itemView.text_repository_name
        val languages: TextView = itemView.text_repository_languages
        val watchers: TextView = itemView.text_repository_watchers

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(currentList[adapterPosition])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(project: Project)
    }
}

class NewsFeedDiffCallback : DiffUtil.ItemCallback<Project>() {
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.languages == newItem.languages &&
                oldItem.watchers == newItem.watchers &&
                oldItem.openIssues == newItem.openIssues &&
                oldItem.createdAt == newItem.createdAt &&
                oldItem.cloneUrl == newItem.cloneUrl
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}