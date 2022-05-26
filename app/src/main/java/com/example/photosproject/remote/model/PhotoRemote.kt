package com.example.photosproject.remote.model

import com.google.gson.annotations.SerializedName

data class PhotoRemote(
    @SerializedName("id") val id: Long?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?
)