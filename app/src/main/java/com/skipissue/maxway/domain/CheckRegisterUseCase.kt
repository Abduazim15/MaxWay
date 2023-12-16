package com.skipissue.maxway.domain

import com.skipissue.maxway.data.constants.Errors
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.data.repos.ProductsRepository
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.domain.entity.PhoneEntity
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class CheckRegisterUseCase @Inject constructor(private val repository: ProductsRepository, private val settings: Settings) {
    suspend operator fun invoke(number: String): State {
        try {
            if (number.length != 13)
                return State.Error(Errors.NUMBER_ERROR)
            val response = repository.checkRegister(settings.shipperId!!, PhoneEntity(number))
            if (response.body() == null)
                return State.Error(Errors.IS_REGISTERED)
            return State.Success(response.body())
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is IOException) return State.NoNetwork
            return State.Error(Errors.IS_REGISTERED)
        }
    }
}