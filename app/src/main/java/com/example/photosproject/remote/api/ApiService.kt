package com.example.photosproject.remote.api

import com.example.photosproject.remote.model.UserRemote
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    fun getUsers(): Single<List<UserRemote>>
}