package com.skipissue.maxway.data.api

import com.skipissue.maxway.domain.entity.RegisterConfirmEntity
import com.skipissue.maxway.domain.entity.UpdateEntity
import com.skipissue.maxway.domain.entity.responses.CheckRegister
import com.skipissue.maxway.domain.entity.responses.FillialsResponse
import com.skipissue.maxway.domain.entity.responses.OrderHistoryResponse
import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import com.skipissue.maxway.domain.entity.responses.ProfileResponse
import com.skipissue.maxway.domain.entity.responses.ProductDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductSuggestionResponse
import com.skipissue.maxway.domain.entity.responses.RegisterConfirmResponse
import com.skipissue.maxway.domain.entity.responses.RegisterResponse
import com.skipissue.maxway.domain.entity.responses.UpdateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MaxWayAPI {
    @GET("v1/core/get/category-with-products/")
    suspend fun getProducts() : Response<ProductsResponse>

    @GET("v1/core/get/product-detail/{id}/?format=json")
    suspend fun getProductsWithDetail(@Path("id") id: String) : Response<ProductsDetailResponse>
    @POST("v1/customers/phone")
    suspend fun checkRegister(@Header("Shipper") shipper : String,@Body phone: String) : Response<CheckRegister>

    @GET("v1/branches?limit=100")
    suspend fun getFillials(@Header("Shipper") shipper : String) : Response<FillialsResponse>
    @GET("v1/customer-profile")
    suspend fun getProfile(@Header("Authorization") token : String) : Response<ProfileResponse>
    @GET("v1/customers/{USER-ID}/orders")
    suspend fun getOrderHistory(@Header("Authorization") token : String,@Path("USER-ID") userId: String) : Response<OrderHistoryResponse>
    @GET("v2/modifier")
    suspend fun getProductDetail(@Header("Authorization") token : String,@Query("product_id") productId: String) : Response<ProductDetailResponse>
     @GET("v2/product-favourites")
    suspend fun getProductSuggestion(@Header("Authorization") token : String,@Query("product_ids") productId: String) : Response<ProductSuggestionResponse>
    @POST("v1/customers/register")
    suspend fun register(@Header("Shipper") shipper : String,@Body phone: String) : Response<RegisterResponse>
    @POST("v1/customers/login")
    suspend fun login(@Header("Shipper") shipper : String,@Body phone: String) : Response<RegisterResponse>
    @POST("v1/customers/register-confirm")
    suspend fun registerConfirm(@Header("Shipper") shipper : String,@Body entity: RegisterConfirmEntity) : Response<RegisterConfirmResponse>
    @POST("v1/customers/confirm-login")
    suspend fun loginConfirm(@Header("Shipper") shipper : String,@Body entity: RegisterConfirmEntity) : Response<RegisterConfirmResponse>
    @PUT("v1/customers/{userId}")
    suspend fun updateUser(@Path("userId") userId: String,@Header("Authorization") token : String, @Body entity: UpdateEntity): Response<UpdateResponse>

}