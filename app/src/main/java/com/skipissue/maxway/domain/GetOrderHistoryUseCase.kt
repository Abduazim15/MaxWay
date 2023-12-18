package com.skipissue.maxway.domain

import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.data.repos.ProductsRepository
import com.skipissue.maxway.data.settings.Settings
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetOrderHistoryUseCase @Inject constructor(private val repository: ProductsRepository,private val settings: Settings) {
    suspend operator fun invoke(): State {
        try {
            val response = repository.getOrderHistory(settings.accessToken!!, settings.id!!)
            return State.Success(response.body())
        } catch (e: Exception) {
            throw e
            e.printStackTrace()
            if (e is IOException) return State.NoNetwork
            return State.Error(1)
        }
    }
}