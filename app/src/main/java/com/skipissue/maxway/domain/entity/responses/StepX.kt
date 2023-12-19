package com.skipissue.maxway.domain.entity.responses

data class StepX(
    val address: String,
    val branch_id: String,
    val branch_name: String,
    val description: String,
    val destination_address: String,
    val external_step_id: Any,
    val id: String,
    val location: LocationXXX,
    val menu_id: String,
    val order_no: String,
    val phone_number: String,
    val products: List<ProductX>,
    val status: String,
    val step_amount: Int,
    val step_discount_price: Int
)