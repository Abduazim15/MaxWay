package com.skipissue.maxway.data.repos

import com.skipissue.maxway.data.datasource.DataBaseDataSource
import com.skipissue.maxway.domain.entity.FoodHistoryEntity
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(val dataSource: DataBaseDataSource):
    DatabaseRepository {
    override suspend fun insert(entity: FoodHistoryEntity) {
        return dataSource.insert(entity)
    }

    override suspend fun getAll(): List<FoodHistoryEntity> {
        return dataSource.getAll()
    }

    override suspend fun get(id: Int): FoodHistoryEntity {
        return dataSource.get(id)
    }

    override suspend fun delete() {
        return dataSource.delete()
    }

    override suspend fun getFromId(id: String): FoodHistoryEntity? {
        return dataSource.getFromId(id)
    }

    override suspend fun increaseAmount(id: Int, amount: Int) {
        return dataSource.increaseAmount(id, amount)
    }

}