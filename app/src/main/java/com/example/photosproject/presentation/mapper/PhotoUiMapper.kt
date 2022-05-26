package com.example.photosproject.presentation.mapper

import com.example.photosproject.domain.model.Photo
import com.example.photosproject.presentation.model.PhotoUiModel
import javax.inject.Inject

class PhotoUiMapper @Inject constructor() {

    fun mapList(photos: List<Photo>): List<PhotoUiModel> {
        return photos.map(::map)
    }

    private fun map(input: Photo): PhotoUiModel {
        return PhotoUiModel(
            id = input.id,
            title = input.title,
            url = input.url
        )
    }
}