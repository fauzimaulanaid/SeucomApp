package com.fauzimaulana.seucomapp.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fauzimaulana.seucomapp.core.domain.model.FloorModel
import com.fauzimaulana.seucomapp.databinding.ItemListBinding
import com.fauzimaulana.seucomapp.view.detail.DetailActivity

class FloorAdapter: ListAdapter<FloorModel, FloorAdapter.FloorViewHolder>(DIFF_CALLBACK) {
    class FloorViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(floor: FloorModel) {
            with(binding) {
                textViewName.text = floor.locName
                textViewType.text = floor.locTypeLabel
                textViewCode.text = floor.locCode
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_FLOOR, floor)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FloorViewHolder {
        val itemsFloorBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FloorViewHolder(itemsFloorBinding)
    }

    override fun onBindViewHolder(holder: FloorViewHolder, position: Int) {
        val floor = getItem(position)
        holder.bind(floor)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FloorModel>() {
            override fun areItemsTheSame(oldItem: FloorModel, newItem: FloorModel): Boolean {
                return oldItem.locID == newItem.locID
            }

            override fun areContentsTheSame(oldItem: FloorModel, newItem: FloorModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}