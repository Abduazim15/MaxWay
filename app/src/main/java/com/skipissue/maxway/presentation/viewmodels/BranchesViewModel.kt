package com.skipissue.maxway.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.domain.GetFillialsUseCase
import com.skipissue.maxway.domain.UpdateUserUseCase
import com.skipissue.maxway.domain.entity.UpdateEntity
import com.skipissue.maxway.domain.entity.responses.FillialsResponse
import com.skipissue.maxway.domain.entity.responses.UpdateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BranchesViewModel @Inject constructor(
    private val getFillialsUseCase: GetFillialsUseCase
): ViewModel() {
    private val _successFlow = MutableSharedFlow<FillialsResponse>()
    val stateSuccess: SharedFlow<FillialsResponse> = _successFlow

    private val _errorFlow = MutableSharedFlow<Int>()
    val errorFlow: SharedFlow<Int> = _errorFlow

    private val _networkFlow = MutableSharedFlow<Unit>()
    val networkFlow: SharedFlow<Unit> = _networkFlow
    init {
        getBranches()
    }
    fun getBranches(){
        viewModelScope.launch {
            val state = getFillialsUseCase.invoke()
            handleState(state)
        }
    }

    private suspend fun handleState(state: State) {
        when(state){
            is State.Error -> _errorFlow.emit(state.code)
            State.NoNetwork -> _networkFlow.emit(Unit)
            is State.Success<*> -> _successFlow.emit(state.data as FillialsResponse)
        }
    }
}