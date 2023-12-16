package com.skipissue.maxway.domain.entity.responses

data class Courier(
    val courier_type: Any,
    val first_name: String,
    val last_name: String,
    val location: LocationX,
    val phone: String,
    val vehicle_number: String
)