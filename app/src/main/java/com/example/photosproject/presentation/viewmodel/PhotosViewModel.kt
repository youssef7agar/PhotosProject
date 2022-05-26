package com.example.photosproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photosproject.domain.model.Photo
import com.example.photosproject.domain.usecase.GetPhotosUseCase
import com.example.photosproject.presentation.mapper.PhotoUiMapper
import com.example.photosproject.presentation.viewstate.PhotosViewState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

class PhotosViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val photoUiMapper: PhotoUiMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _viewState = MutableLiveData<PhotosViewState>()
    val viewState: LiveData<PhotosViewState>
        get() = _viewState

    private val photos = mutableListOf<Photo>()

    fun getPhotos(albumId: Long) {
        _viewState.postValue(PhotosViewState.Loading)

        getPhotosUseCase.execute(albumId = albumId)
            .subscribeWith(PhotosSubscriber())
            .also(compositeDisposable::add)
    }

    private inner class PhotosSubscriber : DisposableSingleObserver<List<Photo>>() {
        override fun onSuccess(t: List<Photo>) {
            photos.clear()
            photos.addAll(t)

            _viewState.postValue(
                PhotosViewState.Success(
                    photos = photoUiMapper.mapList(photos)
                )
            )
        }

        override fun onError(e: Throwable) {
            _viewState.postValue(PhotosViewState.Error)
            Timber.v("Something went wrong while loading the photos. $e")
        }
    }

    fun searchPhotos(title: String) {
        if (title.isNotBlank()) {
            _viewState.postValue(
                PhotosViewState.Success(
                    photos = photoUiMapper.mapList(photos.filter { photo ->
                        photo.title.contains(
                            title
                        )
                    })
                )
            )
        } else {
            _viewState.postValue(
                PhotosViewState.Success(
                    photos = photoUiMapper.mapList(photos)
                )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}