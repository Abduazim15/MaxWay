package com.skipissue.maxway.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skipissue.maxway.data.dao.DatabaseDao
import com.skipissue.maxway.domain.entity.FoodHistoryEntity

@Database(entities = [FoodHistoryEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}