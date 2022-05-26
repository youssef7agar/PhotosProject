package com.example.photosproject

import com.example.photosproject.domain.model.Album
import com.example.photosproject.presentation.model.AlbumUiModel

object AlbumFactory {

    fun makeAlbum() = Album(
        id = 12L,
        title = "TITLE"
    )

    fun makeAlbumUiModel() = AlbumUiModel(
        id = 12L,
        title = "TITLE"
    )
}