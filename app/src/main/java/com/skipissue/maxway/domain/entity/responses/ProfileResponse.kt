package com.skipissue.maxway.domain.entity.responses

data class ProfileResponse(
    val botLanguage: String,
    val createdAt: String,
    val fcmToken: String,
    val id: String,
    val name: String,
    val phone: String,
    val platformId: String,
    val registrationSource: String,
    val shipperId: String
)