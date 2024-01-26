package com.skipissue.maxway.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.skipissue.maxway.data.database.AppDataBase
import com.skipissue.maxway.data.datasource.DataBaseDataSource
import com.skipissue.maxway.domain.entity.FoodHistoryEntity
import javax.inject.Inject

class DataBaseDataSourceImpl @Inject constructor(val database: AppDataBase) : DataBaseDataSource {
    override suspend fun insert(entities: FoodHistoryEntity) {
        withContext(Dispatchers.IO) {
            database.databaseDao().insert(entities) }
    }

    override suspend fun getAll(): List<FoodHistoryEntity> {
        return withContext(Dispatchers.IO){database.databaseDao().getAll()}
    }

    override suspend fun get(id: Int): FoodHistoryEntity {
        return withContext(Dispatchers.IO){database.databaseDao().get(id)}
    }

    override suspend fun delete() {
        withContext(Dispatchers.IO){
            database.databaseDao().delete()}
    }

    override suspend fun deleteById(id: Int) {
        withContext(Dispatchers.IO){
            database.databaseDao().deleteById(id)
        }
    }

    override suspend fun getFromId(id: String): FoodHistoryEntity? {
        return withContext(Dispatchers.IO){database.databaseDao().getFromId(id)}
    }

    override suspend fun increaseAmount(id: Int, amount: Int) {
        withContext(Dispatchers.IO){
            database.databaseDao().increaseAmount(id, amount)}
    }
}