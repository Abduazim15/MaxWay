package com.skipissue.maxway.data.repos

import com.skipissue.maxway.domain.entity.RegisterConfirmEntity
import com.skipissue.maxway.domain.entity.UpdateEntity
import com.skipissue.maxway.domain.entity.responses.CheckRegister
import com.skipissue.maxway.domain.entity.responses.FillialsResponse
import com.skipissue.maxway.domain.entity.responses.OrderHistoryResponse
import com.skipissue.maxway.domain.entity.responses.ProductDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductSuggestionResponse
import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import com.skipissue.maxway.domain.entity.responses.ProfileResponse
import com.skipissue.maxway.domain.entity.responses.RegisterConfirmResponse
import com.skipissue.maxway.domain.entity.responses.RegisterResponse
import com.skipissue.maxway.domain.entity.responses.UpdateResponse
import retrofit2.Response

interface ProductsRepository{
    suspend fun getProducts(): Response<ProductsResponse>
    suspend fun getProductsWithDetail(id: String): Response<ProductsDetailResponse>
    suspend fun checkRegister( shipper : String,  phone: String) : Response<CheckRegister>

    suspend fun getFillials( shipper : String) : Response<FillialsResponse>
    suspend fun getProfile( token : String) : Response<ProfileResponse>
    suspend fun getOrderHistory( token : String,  userId: String) : Response<OrderHistoryResponse>

    suspend fun getProductDetail( token : String,  productId: String) : Response<ProductDetailResponse>
    suspend fun getProductSuggestion( token : String,  productId: String) : Response<ProductSuggestionResponse>

    suspend fun register( shipper : String,  phone: String) : Response<RegisterResponse>

    suspend fun login( shipper : String, phone: String) : Response<RegisterResponse>

    suspend fun registerConfirm( shipper : String, entity: RegisterConfirmEntity) : Response<RegisterConfirmResponse>

    suspend fun loginConfirm( shipper : String,  entity: RegisterConfirmEntity) : Response<RegisterConfirmResponse>

    suspend fun updateUser( userId: String,  token : String,  entity: UpdateEntity): Response<UpdateResponse>
}