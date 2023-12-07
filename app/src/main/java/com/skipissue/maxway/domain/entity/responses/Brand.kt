package com.skipissue.maxway.domain.entity.responses

data class Brand(
    val description: Description,
    val id: String,
    val image: String,
    val is_active: Boolean,
    val order_no: String,
    val parent_id: String,
    val slug: String,
    val title: TitleX
)