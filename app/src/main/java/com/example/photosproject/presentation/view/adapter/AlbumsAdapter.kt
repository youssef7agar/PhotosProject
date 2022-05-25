package com.example.photosproject.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.photosproject.databinding.AdapterAlbumBinding
import com.example.photosproject.presentation.model.AlbumUiModel

class AlbumsAdapter : ListAdapter<AlbumUiModel, AlbumsAdapter.AlbumViewHolder>(AlbumDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            binding = AdapterAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(album = getItem(position))
    }

    inner class AlbumViewHolder(private val binding: AdapterAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: AlbumUiModel) {
            binding.albumTitleTextView.text = album.title
        }
    }
}