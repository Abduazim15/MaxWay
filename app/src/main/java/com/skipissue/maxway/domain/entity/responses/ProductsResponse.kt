package com.skipissue.maxway.domain.entity.responses

data class ProductsResponse(
    val categories: List<Category>,
    val count: String
)