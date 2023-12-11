package com.skipissue.maxway.data.repos

import com.skipissue.maxway.data.datasource.ProductsDataSource
import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import retrofit2.Response
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val dataSource: ProductsDataSource): ProductsRepository {
    override suspend fun getProducts(): Response<ProductsResponse> {
        return dataSource.getProducts()
    }

    override suspend fun getProductsWithDetail(id: String): Response<ProductsDetailResponse> {
        return dataSource.getProductsWithDetail(id)
    }
}