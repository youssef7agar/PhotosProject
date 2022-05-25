package com.example.photosproject.remote.model

import com.google.gson.annotations.SerializedName

data class AlbumRemote(
    @SerializedName("id") val id: Long?,
    @SerializedName("title") val title: String?
)