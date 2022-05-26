package com.example.photosproject.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumUiModel(
    val id: Long,
    val title: String
): Parcelable