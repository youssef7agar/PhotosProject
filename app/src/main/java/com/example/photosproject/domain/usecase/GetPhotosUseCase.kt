package com.example.photosproject.domain.usecase

import com.example.photosproject.domain.model.Photo
import com.example.photosproject.domain.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: Repository
) {
    fun execute(albumId: Long): Single<List<Photo>> {
        return repository.getPhotos(albumId = albumId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}