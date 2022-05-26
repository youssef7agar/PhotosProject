package com.example.photosproject.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.photosproject.presentation.model.AlbumUiModel

object AlbumDiffUtil : DiffUtil.ItemCallback<AlbumUiModel>() {

    override fun areItemsTheSame(oldItem: AlbumUiModel, newItem: AlbumUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumUiModel, newItem: AlbumUiModel): Boolean {
        return oldItem == newItem
    }
}