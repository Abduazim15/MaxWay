package com.skipissue.maxway.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
class FoodHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name : String,
    val foodId: String,
    val imageId: String,
    val quantity: Int,
    val price: Int
)