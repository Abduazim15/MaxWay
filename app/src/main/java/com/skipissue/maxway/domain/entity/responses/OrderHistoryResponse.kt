package com.skipissue.maxway.domain.entity.responses

data class OrderHistoryResponse(
    val count: String,
    val orders: List<Order>
)