package com.skipissue.maxway.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.domain.GetAboutOrderUseCase
import com.skipissue.maxway.domain.GetOrderHistoryUseCase
import com.skipissue.maxway.domain.entity.responses.AboutOrderResponse
import com.skipissue.maxway.domain.entity.responses.OrderHistoryResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val getOrdersUseCase: GetOrderHistoryUseCase, private val getAboutOrderUseCase: GetAboutOrderUseCase): ViewModel() {
    private val _successFlow = MutableSharedFlow<OrderHistoryResponse>()
    val stateSuccess : SharedFlow<OrderHistoryResponse> = _successFlow

    private val _successDFlow = MutableSharedFlow<AboutOrderResponse>()
    val stateSuccessD : SharedFlow<AboutOrderResponse> = _successDFlow

    private val _errorFlow= MutableSharedFlow<Int>()
    val errorFlow: SharedFlow<Int> = _errorFlow

    private val _networkFlow= MutableSharedFlow<Unit>()
    val networkFlow: SharedFlow<Unit> = _networkFlow
    fun getOrders(){
        viewModelScope.launch {
            val state = getOrdersUseCase.invoke()
            handleState(state)
        }
    }
    fun getAboutOrder(id: String){
        viewModelScope.launch {
            val state = getAboutOrderUseCase.invoke(id)
            handleState(state)
        }
    }
    private suspend fun handleState(state: State) {
        when(state){
            is State.Error -> _errorFlow.emit(state.code)
            State.NoNetwork -> _networkFlow.emit(Unit)
            is State.Success<*> -> try {
                _successFlow.emit(state.data as OrderHistoryResponse)
            } catch (e: Exception){
                _successDFlow.emit(state.data as AboutOrderResponse)
            }
        }
    }
}