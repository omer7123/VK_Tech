package com.example.vktech.ui.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vktech.databinding.ItemSeriesBinding
import com.example.vktech.domain.entity.VideoInfoEntity
import com.example.vktech.util.load
import com.example.vktech.util.toDurationFormat

class VideoAdapter(private val onItemClickListener: (VideoInfoEntity) -> Unit): ListAdapter<VideoInfoEntity, VideoAdapter.ViewHolder>(VideoDiffCallback()) {
    inner class ViewHolder(private val binding: ItemSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: VideoInfoEntity) {
            binding.titleTv.text = item.tag
            binding.avaIv.load(item.video.thumbnail)
            binding.timeTv.text = item.duration.toDurationFormat()

            binding.root.setOnClickListener{
                onItemClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class VideoDiffCallback: DiffUtil.ItemCallback<VideoInfoEntity>(){
    override fun areItemsTheSame(oldItem: VideoInfoEntity, newItem: VideoInfoEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VideoInfoEntity, newItem: VideoInfoEntity): Boolean {
        return oldItem == newItem
    }
}