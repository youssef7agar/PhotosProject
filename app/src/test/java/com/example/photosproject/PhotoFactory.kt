package com.example.photosproject

import com.example.photosproject.domain.model.Photo
import com.example.photosproject.presentation.model.PhotoUiModel

object PhotoFactory {

    fun makePhoto() = Photo(
        id = 15L,
        title = "PHOTO TITLE",
        url = "PHOTO URL"
    )

    fun makePhotoUiModel() = PhotoUiModel(
        id = 15L,
        title = "PHOTO TITLE",
        url = "PHOTO URL"
    )
}