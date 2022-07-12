package com.fauzimaulana.seucomapp.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fauzimaulana.seucomapp.core.domain.model.ProjectModel
import com.fauzimaulana.seucomapp.databinding.ItemListBinding
import com.fauzimaulana.seucomapp.view.detail.DetailActivity

class ProjectAdapter: ListAdapter<ProjectModel, ProjectAdapter.ProjectViewHolder>(DIFF_CALLBACK) {
    class ProjectViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(project: ProjectModel) {
            with(binding) {
                textViewName.text = project.locName
                textViewType.text = project.locTypeLabel
                textViewCode.text = project.locCode
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, project)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val itemsProjectBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(itemsProjectBinding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = getItem(position)
        holder.bind(project)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProjectModel>() {
            override fun areItemsTheSame(oldItem: ProjectModel, newItem: ProjectModel): Boolean {
                return oldItem.locID == newItem.locID
            }

            override fun areContentsTheSame(oldItem: ProjectModel, newItem: ProjectModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}