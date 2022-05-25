package com.example.photosproject.domain.repository

import com.example.photosproject.domain.mapper.UserMapper
import com.example.photosproject.domain.model.User
import com.example.photosproject.remote.api.ApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val userMapper: UserMapper
) {

    fun getUsers(): Single<List<User>> {
        return apiService.getUsers().map { usersRemote -> userMapper.mapList(usersRemote) }
    }
}