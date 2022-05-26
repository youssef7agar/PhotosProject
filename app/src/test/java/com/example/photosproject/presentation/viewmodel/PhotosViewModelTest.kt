package com.example.photosproject.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.photosproject.PhotoFactory
import com.example.photosproject.domain.model.Photo
import com.example.photosproject.domain.usecase.GetPhotosUseCase
import com.example.photosproject.presentation.mapper.PhotoUiMapper
import com.example.photosproject.presentation.model.PhotoUiModel
import com.example.photosproject.presentation.viewstate.PhotosViewState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PhotosViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val getPhotosUseCase = mock<GetPhotosUseCase>()
    private val photoUiMapper = mock<PhotoUiMapper>()
    private val viewModel: PhotosViewModel by lazy {
        PhotosViewModel(
            getPhotosUseCase = getPhotosUseCase,
            photoUiMapper = photoUiMapper
        )
    }

    @Test
    fun `view state is loading state when loading photos`() {
        stubGetPhotosUseCase(Single.never())

        viewModel.getPhotos(13L)

        assertEquals(
            PhotosViewState.Loading,
            viewModel.viewState.value
        )
    }

    @Test
    fun `view model calls the get photos use case when loading photos`() {
        stubGetPhotosUseCase(Single.never())
        val albumId = 13L

        viewModel.getPhotos(albumId)

        verify(getPhotosUseCase).execute(albumId)
    }

    @Test
    fun `view state is error when something goes wrong while loading photos`() {
        stubGetPhotosUseCase(Single.error(Throwable()))
        val albumId = 13L

        viewModel.getPhotos(albumId)

        assertEquals(
            PhotosViewState.Error,
            viewModel.viewState.value
        )
    }

    @Test
    fun `view state is success when getPhotosUseCase returns data`() {
        val photos = listOf(PhotoFactory.makePhoto())
        val photosUiModel = listOf(PhotoFactory.makePhotoUiModel())

        stubGetPhotosUseCase(Single.just(photos))
        stubMapPhotos(photosUiModel)
        val albumId = 13L

        viewModel.getPhotos(albumId)

        assertEquals(
            PhotosViewState.Success(
                photos = photosUiModel
            ),
            viewModel.viewState.value
        )
    }

    @Test
    fun `photos list is stored when getPhotosUseCase returns data`() {
        val photos = listOf(PhotoFactory.makePhoto())
        val photosUiModel = listOf(PhotoFactory.makePhotoUiModel())

        stubGetPhotosUseCase(Single.just(photos))
        stubMapPhotos(photosUiModel)
        val albumId = 13L

        viewModel.getPhotos(albumId)

        assertEquals(
            photos,
            viewModel.photos
        )
    }


    private fun stubGetPhotosUseCase(single: Single<List<Photo>>) {
        whenever(getPhotosUseCase.execute(any()))
            .thenReturn(single)
    }

    private fun stubMapPhotos(photos: List<PhotoUiModel>) {
        whenever(photoUiMapper.mapList(any()))
            .thenReturn(photos)
    }
}