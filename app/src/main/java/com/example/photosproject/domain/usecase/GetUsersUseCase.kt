package com.example.photosproject.domain.usecase

import com.example.photosproject.domain.model.User
import com.example.photosproject.domain.repository.Repository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: Repository
) {
    fun execute(): Single<List<User>> {
        return repository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}