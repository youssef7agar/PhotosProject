package com.example.photosproject.presentation.mapper

import com.example.photosproject.domain.model.Album
import com.example.photosproject.presentation.model.AlbumUiModel
import javax.inject.Inject

class AlbumUiMapper @Inject constructor() {

    fun mapList(albums: List<Album>): List<AlbumUiModel> {
        return albums.map(::map)
    }

    private fun map(input: Album): AlbumUiModel {

        return AlbumUiModel(
            id = input.id,
            title = input.title
        )
    }
}