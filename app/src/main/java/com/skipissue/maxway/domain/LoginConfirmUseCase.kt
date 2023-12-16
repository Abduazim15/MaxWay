package com.skipissue.maxway.domain

import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.data.repos.ProductsRepository
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.domain.entity.RegisterConfirmEntity
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class LoginConfirmUseCase @Inject constructor(private val repository: ProductsRepository,private val settings: Settings) {
    suspend operator fun invoke(entity: RegisterConfirmEntity): State {
        try {

            return State.Success(repository.loginConfirm(settings.shipperId!!, entity).body())
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is IOException) return State.NoNetwork
            return State.Error(1)
        }
    }
}