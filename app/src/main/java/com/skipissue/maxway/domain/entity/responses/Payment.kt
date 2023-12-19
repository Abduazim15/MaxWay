package com.skipissue.maxway.domain.entity.responses

data class Payment(
    val created_at: String,
    val id: String,
    val order_id: String,
    val paid_amount: Int,
    val payment_type: String,
    val returned_amount: Int
)