package com.example.photosproject.presentation.mapper

import com.example.photosproject.domain.model.Address
import com.example.photosproject.domain.model.User
import com.example.photosproject.presentation.model.UserUiModel
import java.lang.StringBuilder
import javax.inject.Inject

class UserUiMapper @Inject constructor(){

    fun map(user: User): UserUiModel {
        return UserUiModel(
            name = user.name,
            address = getAddress(user.address)
        )
    }

    private fun getAddress(address: Address): String {
        val stringBuilder = StringBuilder()

        if (address.street.isNotBlank()) {
            stringBuilder.append(address.street)
            if (address.suite.isNotBlank())
                stringBuilder.append(", ")
        }

        if (address.suite.isNotBlank()) {
            stringBuilder.append(address.suite)
            if (address.city.isNotBlank())
                stringBuilder.append(", ")
        }

        if (address.city.isNotBlank()) {
            stringBuilder.append(address.city)
            if (address.zipcode.isNotBlank()){
                stringBuilder.append(", ")
                stringBuilder.append("\n")
            }
        }

        if (address.zipcode.isNotBlank())
            stringBuilder.append(address.zipcode)

        return stringBuilder.toString()
    }
}