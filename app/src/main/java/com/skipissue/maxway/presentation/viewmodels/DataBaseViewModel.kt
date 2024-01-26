package com.skipissue.maxway.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipissue.maxway.data.repos.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.skipissue.maxway.domain.entity.FoodHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltViewModel
class DataBaseViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) : ViewModel() {
    private val _livedata = MutableLiveData<List<FoodHistoryEntity>>()
    val livedata : LiveData<List<FoodHistoryEntity>> = _livedata
    private val _liveSingledata = MutableLiveData<FoodHistoryEntity>()
    val liveSingledata : LiveData<FoodHistoryEntity> = _liveSingledata


    fun insert(entity: FoodHistoryEntity){
        viewModelScope.launch {
            val idF = getFromId(entity.foodId)?.id
            if (idF == null){
                databaseRepository.insert(entity)
            }
            else databaseRepository.increaseAmount(idF.toInt(), entity.quantity)


        }
    }
    fun getAll(){
        viewModelScope.launch {
            _livedata.value = databaseRepository.getAll()
        }
    }
    fun get(id: Int){
        viewModelScope.launch {
            _liveSingledata.value = databaseRepository.get(id)
        }
    }
    fun delete(){
        viewModelScope.launch {
            databaseRepository.delete()
        }
    }
    suspend fun getFromId(id: String): FoodHistoryEntity? {
        return withContext(Dispatchers.IO) {
            databaseRepository.getFromId(id)
        }
    }
    fun increaseAmount(id: Int, amount: Int){
        viewModelScope.launch {
            databaseRepository.increaseAmount(id, amount)
        }
    }
    fun deleteById(id: Int){
        viewModelScope.launch {
            databaseRepository.deleteById(id)
        }
    }
}