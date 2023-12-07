package com.skipissue.maxway.data.api

import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface MaxWayAPI {
    @GET("v1/core/get/category-with-products/")
    suspend fun getProducts() : Response<ProductsResponse>

    @GET("v1/core/get/product-detail/ed7654f9-a6e5-45a9-b8a0-9f150610cc74/?format=json")
    suspend fun getProductsWith() : Response<ProductsDetailResponse>
}