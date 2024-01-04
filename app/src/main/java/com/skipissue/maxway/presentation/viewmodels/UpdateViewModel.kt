package com.skipissue.maxway.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.domain.CheckRegisterUseCase
import com.skipissue.maxway.domain.LoginUseCase
import com.skipissue.maxway.domain.RegisterUseCase
import com.skipissue.maxway.domain.UpdateUserUseCase
import com.skipissue.maxway.domain.entity.UpdateEntity
import com.skipissue.maxway.domain.entity.responses.CheckRegister
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import com.skipissue.maxway.domain.entity.responses.RegisterResponse
import com.skipissue.maxway.domain.entity.responses.UpdateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase
): ViewModel() {
    private val _successFlow = MutableSharedFlow<UpdateResponse>()
    val stateSuccess: SharedFlow<UpdateResponse> = _successFlow

    private val _errorFlow = MutableSharedFlow<Int>()
    val errorFlow: SharedFlow<Int> = _errorFlow

    private val _networkFlow = MutableSharedFlow<Unit>()
    val networkFlow: SharedFlow<Unit> = _networkFlow

    fun update(entity: UpdateEntity){
        viewModelScope.launch {
            val state = updateUserUseCase.invoke(entity)
            handleState(state)
        }
    }

    private suspend fun handleState(state: State) {
        when(state){
            is State.Error -> _errorFlow.emit(state.code)
            State.NoNetwork -> _networkFlow.emit(Unit)
            is State.Success<*> -> _successFlow.emit(state.data as UpdateResponse)
        }
    }
}