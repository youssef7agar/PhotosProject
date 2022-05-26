package com.example.photosproject.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.photosproject.AlbumFactory
import com.example.photosproject.UserFactory
import com.example.photosproject.domain.model.Album
import com.example.photosproject.domain.model.User
import com.example.photosproject.domain.usecase.GetAlbumsUseCase
import com.example.photosproject.domain.usecase.GetUsersUseCase
import com.example.photosproject.presentation.mapper.AlbumUiMapper
import com.example.photosproject.presentation.mapper.UserUiMapper
import com.example.photosproject.presentation.model.AlbumUiModel
import com.example.photosproject.presentation.model.UserUiModel
import com.example.photosproject.presentation.viewstate.UserViewState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val getUserUseCase = mock<GetUsersUseCase>()
    private val getAlbumsUseCase = mock<GetAlbumsUseCase>()
    private val userUiMapper = mock<UserUiMapper>()
    private val albumsUiMapper = mock<AlbumUiMapper>()
    private val viewModel: UserViewModel by lazy {
        UserViewModel(
            getUsersUseCase = getUserUseCase,
            getAlbumsUseCase = getAlbumsUseCase,
            userUiMapper = userUiMapper,
            albumsUiMapper = albumsUiMapper
        )
    }

    @Test
    fun `view state starts with loading state`() {
        stubGetUsers(Single.never())
        stubGetAlbums(Single.never())

        assertEquals(
            UserViewState.Loading,
            viewModel.viewState.value
        )
    }

    @Test
    fun `view state is error when something goes wrong with loading the user`() {
        stubGetUsers(Single.error(Throwable()))
        stubGetAlbums(Single.never())

        assertEquals(
            UserViewState.Error,
            viewModel.viewState.value
        )
    }

    @Test
    fun `view state is error when something goes wrong with loading the albums`() {
        stubGetUsers(Single.never())
        stubGetAlbums(Single.error(Throwable()))

        assertEquals(
            UserViewState.Error,
            viewModel.viewState.value
        )
    }

    @Test
    fun `view state is success when both getUsersUseCase and getAlbumsUseCase return data`() {
        val users = listOf(UserFactory.makeUser())
        val userUiModel = UserFactory.makeUserUiModel()
        val albums = listOf(AlbumFactory.makeAlbum())
        val albumsUiModel = listOf(AlbumFactory.makeAlbumUiModel())

        stubGetUsers(Single.just(users))
        stubGetAlbums(Single.just(albums))
        stubMapUser(userUiModel)
        stubMapAlbums(albumsUiModel)

        assertEquals(
            UserViewState.Success(
                user = userUiModel,
                albums = albumsUiModel
            ),
            viewModel.viewState.value
        )
    }

    private fun stubGetUsers(single: Single<List<User>>) {
        whenever(getUserUseCase.execute(any()))
            .thenReturn(single)
    }

    private fun stubGetAlbums(single: Single<List<Album>>) {
        whenever(getAlbumsUseCase.execute(any()))
            .thenReturn(single)
    }

    private fun stubMapUser(user: UserUiModel) {
        whenever(userUiMapper.map(any()))
            .thenReturn(user)
    }

    private fun stubMapAlbums(albums: List<AlbumUiModel>) {
        whenever(albumsUiMapper.mapList(any()))
            .thenReturn(albums)
    }
}