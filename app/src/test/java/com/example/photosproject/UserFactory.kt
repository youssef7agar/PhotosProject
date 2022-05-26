package com.example.photosproject

import com.example.photosproject.domain.model.Address
import com.example.photosproject.domain.model.User
import com.example.photosproject.presentation.model.UserUiModel

object UserFactory {

    fun makeUser() = User(
        id = 13L,
        name = "USER NAME",
        address = Address(
            street = "STREET",
            suite = "SUITE",
            city = "CITY",
            zipcode = "ZIPCODE"
        )
    )

    fun makeUserUiModel() = UserUiModel(
        name = "USER NAME",
        address = "ADDRESS"
    )
}