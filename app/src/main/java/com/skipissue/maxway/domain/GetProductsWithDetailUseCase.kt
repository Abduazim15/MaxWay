package com.skipissue.maxway.domain

import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.data.repos.ProductsRepository
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetProductsWithDetailUseCase @Inject constructor(private val repository: ProductsRepository) {
    suspend operator fun invoke(id: String): State {
        try {
            return State.Success(repository.getProductsWithDetail(id).body())
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
            if (e is IOException) return State.NoNetwork
            return State.Error(1)
        }
    }
}