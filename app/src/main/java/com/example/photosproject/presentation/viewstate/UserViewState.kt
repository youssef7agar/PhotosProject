package com.example.photosproject.presentation.viewstate

import com.example.photosproject.presentation.model.AlbumUiModel
import com.example.photosproject.presentation.model.UserUiModel

sealed class UserViewState {

    object Loading : UserViewState()

    object Error : UserViewState()

    data class Success(
        val user: UserUiModel,
        val albums: List<AlbumUiModel>
    ) : UserViewState()
}