package com.example.photosproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photosproject.domain.model.User
import com.example.photosproject.domain.usecase.GetUsersUseCase
import com.example.photosproject.presentation.mapper.UserUiMapper
import com.example.photosproject.presentation.viewstate.UserViewState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val userUiMapper: UserUiMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _viewState = MutableLiveData<UserViewState>()
    val viewState: LiveData<UserViewState>
        get() = _viewState

    init {
        getUsers()
    }

    private fun getUsers() {
        _viewState.postValue(UserViewState.Loading)
        compositeDisposable.add(
            getUsersUseCase.execute().subscribeWith(UserSubscriber())
        )
    }

    private inner class UserSubscriber : DisposableSingleObserver<List<User>>() {

        override fun onSuccess(t: List<User>) {
            val randomIndex = Random.nextInt(0, t.size - 1)
            val user = t[randomIndex]

            _viewState.postValue(
                UserViewState.Success(
                    user = userUiMapper.map(user),
                    isAlbumsLoading = true
                )
            )
        }

        override fun onError(e: Throwable) {
            Timber.v("Something went wrong while loading the user's data. $e")
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}