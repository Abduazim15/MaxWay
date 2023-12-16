package com.skipissue.maxway.domain

import com.skipissue.maxway.data.constants.Errors
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.data.repos.ProductsRepository
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.domain.entity.UpdateEntity
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: ProductsRepository,private val settings: Settings) {
    suspend operator fun invoke(entity : UpdateEntity): State {
        try {
            if (entity.phone.length != 13)
                return State.Error(Errors.NUMBER_ERROR)
            else if (entity.name.length < 3)
                return State.Error(Errors.NAME_ERROR)
            return State.Success(repository.updateUser(settings.id!!, settings.accessToken!!, entity).body())
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is IOException) return State.NoNetwork
            return State.Error(1)
        }
    }
}