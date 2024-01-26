package com.skipissue.maxway.data.repos

import com.skipissue.maxway.domain.entity.FoodHistoryEntity

interface DatabaseRepository {
    suspend fun insert(entity: FoodHistoryEntity)
    suspend fun getAll(): List<FoodHistoryEntity>
    suspend fun get(id: Int): FoodHistoryEntity

    suspend fun delete()

    suspend fun deleteById(id: Int)
    suspend fun getFromId(id: String): FoodHistoryEntity?
    suspend fun increaseAmount(id: Int, amount: Int)
}