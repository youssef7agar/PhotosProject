package com.example.photosproject.domain.usecase

import com.example.photosproject.domain.model.Album
import com.example.photosproject.domain.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: Repository
) {
    fun execute(userId: Long): Single<List<Album>> {
        return repository.getAlbums(userId = userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}