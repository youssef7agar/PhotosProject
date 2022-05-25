package com.example.photosproject.domain.mapper

import com.example.photosproject.domain.model.Album
import com.example.photosproject.remote.model.AlbumRemote
import javax.inject.Inject

class AlbumMapper @Inject constructor() {

    fun mapList(input: List<AlbumRemote>): List<Album> {
        return input.map(::map)
    }

    private fun map(input: AlbumRemote): Album {
        assertEssentialParams(remote = input)

        return Album(
            id = input.id!!,
            title = input.title!!
        )
    }

    private fun assertEssentialParams(remote: AlbumRemote) {
        when {
            remote.id == null -> {
                throw RuntimeException("The params: album.id is missing in received object: $remote")
            }
            remote.title == null -> {
                throw RuntimeException("The params: album.title is missing in received object: $remote")
            }
        }
    }
}