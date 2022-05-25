package com.example.photosproject.remote.model

import com.google.gson.annotations.SerializedName

class UserRemote(
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Long?,
    @SerializedName("address") val address: AddressRemote?
)