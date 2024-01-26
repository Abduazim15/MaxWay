package com.skipissue.maxway.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.skipissue.maxway.domain.entity.FoodHistoryEntity

@Dao
interface DatabaseDao {
    @Insert
    fun insert(entity: FoodHistoryEntity)
    @Query("SELECT * FROM basket")
    fun getAll(): List<FoodHistoryEntity>
    @Query("SELECT * FROM basket WHERE id = :id")
    fun get(id: Int): FoodHistoryEntity
    @Query("SELECT * FROM basket WHERE foodId = :id")
    fun getFromId(id: String): FoodHistoryEntity?

    @Query("DELETE FROM basket")
    fun delete()
    @Query("DELETE FROM basket WHERE id = :id")
    fun deleteById(id: Int)

    @Query("UPDATE basket SET quantity = quantity + :incrementAmount WHERE id = :itemId")
    fun increaseAmount(itemId: Int, incrementAmount: Int)
}