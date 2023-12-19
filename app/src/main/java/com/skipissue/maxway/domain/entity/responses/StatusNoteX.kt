package com.skipissue.maxway.domain.entity.responses

data class StatusNoteX(
    val created_at: String,
    val description: String,
    val id: String,
    val order_id: String,
    val reason_id: String,
    val status_id: String,
    val system_user_id: String
)