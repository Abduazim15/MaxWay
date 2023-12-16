package com.skipissue.maxway.domain.entity.responses

data class GroupModifier(
    val active: Boolean,
    val add_to_price: Boolean,
    val category_name: CategoryName,
    val code: String,
    val created_at: String,
    val from_product_id: String,
    val id: String,
    val is_checkbox: Boolean,
    val is_compulsory: Boolean,
    val max_amount: Int,
    val min_amount: Int,
    val name: Name,
    val order: String,
    val price: String,
    val shipper_id: String,
    val to_product_id: String,
    val type: String,
    val variants: List<Variant>
)