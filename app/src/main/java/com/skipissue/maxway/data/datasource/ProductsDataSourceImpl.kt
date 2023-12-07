package com.skipissue.maxway.data.datasource

import com.skipissue.maxway.data.api.MaxWayAPI
import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import retrofit2.Response
import javax.inject.Inject

class ProductsDataSourceImpl @Inject constructor(private val maxWayAPI: MaxWayAPI) :
    ProductsDataSource {
    override suspend fun getProducts(): Response<ProductsResponse> {
        return maxWayAPI.getProducts()
    }

    override suspend fun getProductsWithDetail(): Response<ProductsDetailResponse> {
        return maxWayAPI.getProductsWithDetail()
    }
}