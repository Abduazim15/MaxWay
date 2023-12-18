package com.skipissue.maxway.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.domain.GetOrderHistoryUseCase
import com.skipissue.maxway.domain.GetProductsUseCase
import com.skipissue.maxway.domain.GetProductsWithDetailUseCase
import com.skipissue.maxway.domain.entity.responses.OrderHistoryResponse
import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val getOrdersUseCase: GetOrderHistoryUseCase): ViewModel() {
    private val _successFlow = MutableSharedFlow<OrderHistoryResponse>()
    val stateSuccess : SharedFlow<OrderHistoryResponse> = _successFlow

    private val _successDFlow = MutableSharedFlow<ProductsDetailResponse>()
    val stateSuccessD : SharedFlow<ProductsDetailResponse> = _successDFlow

    private val _errorFlow= MutableSharedFlow<Int>()
    val errorFlow: SharedFlow<Int> = _errorFlow

    private val _networkFlow= MutableSharedFlow<Unit>()
    val networkFlow: SharedFlow<Unit> = _networkFlow
    init {
        getOrders()
    }
    fun getOrders(){
        viewModelScope.launch {
            val state = getOrdersUseCase.invoke()
            handleState(state)
        }
    }
    fun getOrderWithDetail(id: String){
        viewModelScope.launch {

        }
    }
    private suspend fun handleState(state: State) {
        when(state){
            is State.Error -> _errorFlow.emit(state.code)
            State.NoNetwork -> _networkFlow.emit(Unit)
            is State.Success<*> -> _successFlow.emit(state.data as OrderHistoryResponse)
        }
    }
}