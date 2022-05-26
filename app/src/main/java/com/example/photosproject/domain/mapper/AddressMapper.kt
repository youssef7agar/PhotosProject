package com.example.photosproject.domain.mapper

import com.example.photosproject.domain.model.Address
import com.example.photosproject.remote.model.AddressRemote
import javax.inject.Inject

class AddressMapper @Inject constructor() {

    fun map(input: AddressRemote): Address {
        assertEssentialParams(remote = input)

        return Address(
            street = input.street!!,
            suite = input.suite!!,
            city = input.city!!,
            zipcode = input.zipcode!!
        )
    }

    private fun assertEssentialParams(remote: AddressRemote) {
        when {
            remote.street == null -> {
                throw RuntimeException("The params: address.street is missing in received object: $remote")
            }
            remote.suite == null -> {
                throw RuntimeException("The params: address.suite is missing in received object: $remote")
            }
            remote.city == null -> {
                throw RuntimeException("The params: address.city is missing in received object: $remote")
            }
            remote.zipcode == null -> {
                throw RuntimeException("The params: address.zipcode is missing in received object: $remote")
            }
        }
    }
}