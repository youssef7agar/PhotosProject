package com.example.photosproject.domain.mapper

import com.example.photosproject.domain.model.Photo
import com.example.photosproject.remote.model.PhotoRemote
import javax.inject.Inject

class PhotoMapper @Inject constructor() {

    fun mapList(photos: List<PhotoRemote>): List<Photo> {
        return photos.map(::map)
    }

    private fun map(input: PhotoRemote): Photo {
        assertEssentialParams(remote = input)

        return Photo(
            id = input.id!!,
            title = input.title!!,
            url = input.url!!
        )
    }

    private fun assertEssentialParams(remote: PhotoRemote) {
        when {
            remote.id == null -> {
                throw RuntimeException("The params: photo.id is missing in received object: $remote")
            }
            remote.title == null -> {
                throw RuntimeException("The params: photo.title is missing in received object: $remote")
            }
            remote.url == null -> {
                throw RuntimeException("The params: photo.url is missing in received object: $remote")
            }
        }
    }
}