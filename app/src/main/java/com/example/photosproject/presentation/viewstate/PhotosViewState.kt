package com.example.photosproject.presentation.viewstate

import com.example.photosproject.presentation.model.PhotoUiModel

sealed class PhotosViewState {

    object Loading : PhotosViewState()

    object Error : PhotosViewState()

    data class Success(val photos: List<PhotoUiModel>) : PhotosViewState()
}