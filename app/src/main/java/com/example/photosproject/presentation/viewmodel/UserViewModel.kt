package com.example.photosproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photosproject.domain.usecase.GetAlbumsUseCase
import com.example.photosproject.domain.usecase.GetUsersUseCase
import com.example.photosproject.presentation.mapper.AlbumUiMapper
import com.example.photosproject.presentation.mapper.UserUiMapper
import com.example.photosproject.presentation.viewstate.UserViewState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val userUiMapper: UserUiMapper,
    private val albumsUiMapper: AlbumUiMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _viewState = MutableLiveData<UserViewState>()
    val viewState: LiveData<UserViewState>
        get() = _viewState

    val id = Random.nextInt(1, 10).toLong()

    init {
        getUserAndAlbums()
    }

    private fun getUserAndAlbums() {
        _viewState.postValue(UserViewState.Loading)

        val usersDisposable = getUsersUseCase.execute(id = id)
        val albumsDisposable = getAlbumsUseCase.execute(userId = id)

        Single.zip(usersDisposable, albumsDisposable) { usersList, albumsList ->
            val user = usersList.first()

            _viewState.postValue(
                UserViewState.Success(
                    user = userUiMapper.map(user),
                    albums = albumsUiMapper.mapList(albumsList)
                )
            )
        }.subscribe({}, { e ->
            _viewState.postValue(UserViewState.Error)
            Timber.v("Something went wrong while loading the user's data. $e")
        }).also(compositeDisposable::add)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}