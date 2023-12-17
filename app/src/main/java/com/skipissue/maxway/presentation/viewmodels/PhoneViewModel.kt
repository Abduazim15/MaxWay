package com.skipissue.maxway.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipissue.maxway.data.constants.Errors
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.domain.CheckRegisterUseCase
import com.skipissue.maxway.domain.GetProductsUseCase
import com.skipissue.maxway.domain.GetProductsWithDetailUseCase
import com.skipissue.maxway.domain.LoginUseCase
import com.skipissue.maxway.domain.RegisterUseCase
import com.skipissue.maxway.domain.entity.responses.CheckRegister
import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import com.skipissue.maxway.domain.entity.responses.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PhoneViewModel @Inject constructor(
    private val checkRegisterUseCase: CheckRegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _successFlow = MutableSharedFlow<RegisterResponse>()
    val stateSuccess: SharedFlow<RegisterResponse> = _successFlow

    private val _checkFlow = MutableSharedFlow<CheckRegister>()
    val stateCheck: SharedFlow<CheckRegister> = _checkFlow

    private val _errorFlow = MutableSharedFlow<Int>()
    val errorFlow: SharedFlow<Int> = _errorFlow

    private val _networkFlow = MutableSharedFlow<Unit>()
    val networkFlow: SharedFlow<Unit> = _networkFlow
    fun check(number: String) {
        viewModelScope.launch {
            val state = checkRegisterUseCase.invoke(number)
            handleState(state)
        }
    }

    fun register(number: String) {
        viewModelScope.launch {
            val state = registerUseCase.invoke(number)
            handleState(state)
        }
    }

    fun login(number: String) {
        viewModelScope.launch {
            val state = loginUseCase.invoke(number)
            handleState(state)
        }
    }

    private suspend fun handleState(state: State) {
        when (state) {
            is State.Error -> _errorFlow.emit(state.code)
            State.NoNetwork -> _networkFlow.emit(Unit)
            is State.Success<*> -> {
                try {
                    _successFlow.emit(state.data as RegisterResponse)
                } catch (e: Exception) {
                    _checkFlow.emit(state.data as CheckRegister)
                }
            }
        }
    }
}