package com.skipissue.maxway.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.domain.LoginConfirmUseCase
import com.skipissue.maxway.domain.LoginUseCase
import com.skipissue.maxway.domain.RegisterConfirmUseCase
import com.skipissue.maxway.domain.RegisterUseCase
import com.skipissue.maxway.domain.entity.RegisterConfirmEntity
import com.skipissue.maxway.domain.entity.responses.CheckRegister
import com.skipissue.maxway.domain.entity.responses.RegisterConfirmResponse
import com.skipissue.maxway.domain.entity.responses.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(
    private val registerConfirmUseCase: RegisterConfirmUseCase,
    private val loginConfirmUseCase: LoginConfirmUseCase,
) : ViewModel() {
    private val _successFlow = MutableSharedFlow<RegisterConfirmResponse>()
    val stateSuccess: SharedFlow<RegisterConfirmResponse> = _successFlow

    private val _errorFlow = MutableSharedFlow<Int>()
    val errorFlow: SharedFlow<Int> = _errorFlow

    private val _networkFlow = MutableSharedFlow<Unit>()
    val networkFlow: SharedFlow<Unit> = _networkFlow

    fun register(number: String, code: String) {
        viewModelScope.launch {
            val state = registerConfirmUseCase.invoke(RegisterConfirmEntity(code, number))
            handleState(state)
        }
    }

    fun login(number: String, code: String) {
        viewModelScope.launch {
            val state = loginConfirmUseCase.invoke(RegisterConfirmEntity(code, number))
            handleState(state)
        }
    }

    private suspend fun handleState(state: State) {
        when (state) {
            is State.Error -> _errorFlow.emit(state.code)
            State.NoNetwork -> _networkFlow.emit(Unit)
            is State.Success<*> -> { _successFlow.emit(state.data as RegisterConfirmResponse) }
        }
    }
}