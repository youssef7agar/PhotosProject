package com.example.photosproject.remote.api

import com.example.photosproject.remote.model.AlbumRemote
import com.example.photosproject.remote.model.UserRemote
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/users")
    fun getUser(@Query("id") id: Long): Single<List<UserRemote>>

    @GET("/albums")
    fun getAlbums(@Query("userId") userId: Long): Single<List<AlbumRemote>>
}