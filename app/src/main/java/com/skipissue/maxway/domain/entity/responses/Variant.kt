package com.skipissue.maxway.domain.entity.responses

data class Variant(
    val brand_id: String,
    val category_ids: Any,
    val combo_ids: Any,
    val count: String,
    val currency: String,
    val description: DescriptionX,
    val gallery: Any,
    val id: String,
    val image: String,
    val in_price: Int,
    val is_divisible: Boolean,
    val measurement: String,
    val order_no: String,
    val out_price: Int,
    val property_groups: Any,
    val rate_id: String,
    val tag_ids: Any,
    val title: Title
)