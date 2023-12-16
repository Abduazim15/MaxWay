package com.skipissue.maxway.domain.entity.responses

data class CheckRegister(
    val created_at: String,
    val date_of_birth: String,
    val fcm_token: String,
    val id: String,
    val is_blocked: Boolean,
    val minimal_order_price: Int,
    val name: String,
    val phone: String,
    val registration_source: String,
    val tg_chat_id: String
)