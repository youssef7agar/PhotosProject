package com.example.photosproject.domain.mapper

import com.example.photosproject.domain.model.User
import com.example.photosproject.remote.model.UserRemote
import javax.inject.Inject

class UserMapper @Inject constructor(
    private val addressMapper: AddressMapper
) {

    fun map(input: UserRemote): User {
        assertEssentialParams(remote = input)

        return User(
            id = input.id!!,
            name = input.name!!,
            address = addressMapper.map(input.address!!)
        )
    }

    fun mapList(input: List<UserRemote>): List<User> {
        return input.map { userRemote ->
            assertEssentialParams(remote = userRemote)

            User(
                id = userRemote.id!!,
                name = userRemote.name!!,
                address = addressMapper.map(userRemote.address!!)
            )
        }
    }

    private fun assertEssentialParams(remote: UserRemote) {
        when {
            remote.id == null -> {
                throw RuntimeException("The params: user.id is missing in received object: $remote")
            }
            remote.name == null -> {
                throw RuntimeException("The params: user.name is missing in received object: $remote")
            }
            remote.address == null -> {
                throw RuntimeException("The params: user.address is missing in received object: $remote")
            }
        }
    }
}