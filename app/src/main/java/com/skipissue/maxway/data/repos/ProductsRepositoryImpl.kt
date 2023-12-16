package com.skipissue.maxway.data.repos

import com.skipissue.maxway.data.datasource.ProductsDataSource
import com.skipissue.maxway.domain.entity.PhoneEntity
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
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val dataSource: ProductsDataSource): ProductsRepository {
    override suspend fun getProducts(): Response<ProductsResponse> {
        return dataSource.getProducts()
    }

    override suspend fun getProductsWithDetail(id: String): Response<ProductsDetailResponse> {
        return dataSource.getProductsWithDetail(id)
    }
    override suspend fun checkRegister(shipper: String, phone: PhoneEntity): Response<CheckRegister> {
        return dataSource.checkRegister(shipper, phone)
    }
    override suspend fun getFillials(shipper: String): Response<FillialsResponse> {
        return dataSource.getFillials(shipper)
    }

    override suspend fun getProfile(token: String): Response<ProfileResponse> {
        return dataSource.getProfile(token)
    }

    override suspend fun getOrderHistory(
        token: String,
        userId: String
    ): Response<OrderHistoryResponse> {
        return dataSource.getOrderHistory(token, userId)
    }

    override suspend fun getProductDetail(
        token: String,
        productId: String
    ): Response<ProductDetailResponse> {
        return dataSource.getProductDetail(token, productId)
    }

    override suspend fun getProductSuggestion(
        token: String,
        productId: String
    ): Response<ProductSuggestionResponse> {
        return dataSource.getProductSuggestion(token, productId)
    }

    override suspend fun register(shipper: String, phone: PhoneEntity): Response<RegisterResponse> {
        return dataSource.register(shipper, phone)
    }
    override suspend fun login(shipper: String, phone: PhoneEntity): Response<RegisterResponse> {
        return dataSource.login(shipper, phone)
    }
    override suspend fun registerConfirm(
        shipper: String,
        entity: RegisterConfirmEntity
    ): Response<RegisterConfirmResponse> {
        return dataSource.registerConfirm(shipper, entity)
    }

    override suspend fun loginConfirm(
        shipper: String,
        entity: RegisterConfirmEntity
    ): Response<RegisterConfirmResponse> {
        return dataSource.loginConfirm(shipper, entity)
    }

    override suspend fun updateUser(
        userId: String,
        token: String,
        entity: UpdateEntity
    ): Response<UpdateResponse> {
        return dataSource.updateUser(userId, token, entity)
    }
}