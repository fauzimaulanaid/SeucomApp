package com.fauzimaulana.seucomapp.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fauzimaulana.seucomapp.core.domain.model.BuildingModel
import com.fauzimaulana.seucomapp.databinding.ItemListBinding
import com.fauzimaulana.seucomapp.view.detail.DetailActivity

class BuildingAdapter: ListAdapter<BuildingModel, BuildingAdapter.BuildingViewHolder>(DIFF_CALLBACK) {
    class BuildingViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(building: BuildingModel) {
            with(binding) {
                textViewName.text = building.locName
                textViewType.text = building.locTypeLabel
                textViewCode.text = building.locCode
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_BUILDING, building)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val itemsProjectBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuildingViewHolder(itemsProjectBinding)
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        val building = getItem(position)
        holder.bind(building)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BuildingModel>() {
            override fun areItemsTheSame(oldItem: BuildingModel, newItem: BuildingModel): Boolean {
                return oldItem.locID == newItem.locID
            }

            override fun areContentsTheSame(oldItem: BuildingModel, newItem: BuildingModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}