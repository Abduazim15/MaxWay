package com.skipissue.maxway.domain.entity.responses

data class ProductX(
    val client_id: String,
    val description: Any,
    val discount_price: Int,
    val discounts: List<Any>,
    val external_product_id: Any,
    val id: String,
    val image: String,
    val modifiers: Modifiers,
    val name: String,
    val order_modifiers: List<Any>,
    val price: Int,
    val product_id: String,
    val quantity: Int,
    val total_amount: Int,
    val total_modifier_amount: Int,
    val type: String,
    val variants: List<Any>
)