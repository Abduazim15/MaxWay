package com.skipissue.maxway.data.api

import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MaxWayAPI {
    @GET("v1/core/get/category-with-products/")
    suspend fun getProducts() : Response<ProductsResponse>

    @GET("v1/core/get/product-detail/{id}/?format=json")
    suspend fun getProductsWithDetail(@Path("id") id: String) : Response<ProductsDetailResponse>
}