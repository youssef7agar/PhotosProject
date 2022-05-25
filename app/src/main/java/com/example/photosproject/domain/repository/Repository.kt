package com.example.photosproject.domain.repository

import com.example.photosproject.domain.mapper.AlbumMapper
import com.example.photosproject.domain.mapper.UserMapper
import com.example.photosproject.domain.model.Album
import com.example.photosproject.domain.model.User
import com.example.photosproject.remote.api.ApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val userMapper: UserMapper,
    private val albumMapper: AlbumMapper
) {

    fun getUsers(): Single<List<User>> {
        return apiService.getUsers().map(userMapper::mapList)
    }

    fun getAlbums(userId: Long): Single<List<Album>> {
        return apiService.getAlbums(userId).map(albumMapper::mapList)
    }
}