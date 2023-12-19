package com.skipissue.maxway.domain

import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.data.repos.ProductsRepository
import com.skipissue.maxway.data.settings.Settings
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetAboutOrderUseCase @Inject constructor(private val repository: ProductsRepository, private val settings: Settings) {
    suspend operator fun invoke(id: String): State {
        try {
            return State.Success(repository.getAboutOrder(settings.shipperId!!, id, settings.accessToken!!).body())
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is IOException) return State.NoNetwork
            return State.Error(1)
        }
    }
}