package com.example.photosproject.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.photosproject.presentation.model.PhotoUiModel

object PhotoDiffUtil : DiffUtil.ItemCallback<PhotoUiModel>() {

    override fun areItemsTheSame(oldItem: PhotoUiModel, newItem: PhotoUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoUiModel, newItem: PhotoUiModel): Boolean {
        return oldItem == newItem
    }
}