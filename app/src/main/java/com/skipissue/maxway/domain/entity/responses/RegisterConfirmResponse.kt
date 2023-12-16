package com.skipissue.maxway.domain.entity.responses

data class RegisterConfirmResponse(
    val access_token: String,
    val created_at: String,
    val id: String,
    val is_blocked: Boolean,
    val minimal_order_price: Int,
    val name: String,
    val phone: String,
    val refresh_token: String,
    val updated_at: String
)