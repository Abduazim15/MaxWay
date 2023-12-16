package com.skipissue.maxway.domain.entity.responses

data class StatusNote(
    val created_at: String,
    val description: String,
    val id: String,
    val status_id: String
)