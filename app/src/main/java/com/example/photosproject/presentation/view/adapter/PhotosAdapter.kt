package com.example.photosproject.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photosproject.R
import com.example.photosproject.databinding.AdapterPhotoBinding
import com.example.photosproject.presentation.model.PhotoUiModel

class PhotosAdapter(private val clickOnPhoto: (String) -> Unit) :
    ListAdapter<PhotoUiModel, PhotosAdapter.PhotoViewHolder>(PhotoDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            binding = AdapterPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PhotoViewHolder(private val binding: AdapterPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: PhotoUiModel) {
            Glide.with(binding.root)
                .load(photo.url)
                .error(R.drawable.ic_baseline_image_24)
                .into(binding.photoImageView)

            binding.photoImageView.setOnClickListener { clickOnPhoto(photo.url) }
        }
    }
}