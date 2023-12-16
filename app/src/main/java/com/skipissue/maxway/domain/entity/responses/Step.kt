package com.skipissue.maxway.domain.entity.responses

data class Step(
    val address: String,
    val branch_id: String,
    val branch_name: String,
    val description: String,
    val destination_address: String,
    val location: LocationXX,
    val phone_number: String
)