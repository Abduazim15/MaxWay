package com.skipissue.maxway.domain

import com.skipissue.maxway.data.constants.Errors
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.data.repos.ProductsRepository
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.domain.entity.PhoneEntity
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: ProductsRepository,private val settings: Settings) {
    suspend operator fun invoke(phone: String): State {
        try {
            if (phone.length != 13)
                return State.Error(Errors.NUMBER_ERROR)

            return State.Success(repository.register(settings.shipperId!!, PhoneEntity(phone)).body())
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is IOException) return State.NoNetwork
            return State.Error(1)
        }
    }
}