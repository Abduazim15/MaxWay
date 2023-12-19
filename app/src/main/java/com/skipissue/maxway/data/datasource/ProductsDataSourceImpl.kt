package com.skipissue.maxway.data.datasource

import com.skipissue.maxway.data.api.CustomerAPI
import com.skipissue.maxway.data.api.MaxWayAPI
import com.skipissue.maxway.domain.entity.PhoneEntity
import com.skipissue.maxway.domain.entity.RegisterConfirmEntity
import com.skipissue.maxway.domain.entity.UpdateEntity
import com.skipissue.maxway.domain.entity.responses.AboutOrderResponse
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

class ProductsDataSourceImpl @Inject constructor(private val maxWayAPI: MaxWayAPI, private val customerAPI: CustomerAPI) :
    ProductsDataSource {
    override suspend fun getProducts(): Response<ProductsResponse> {
        return maxWayAPI.getProducts()
    }

    override suspend fun getProductsWithDetail(id: String): Response<ProductsDetailResponse> {
        return maxWayAPI.getProductsWithDetail(id)
    }

    override suspend fun checkRegister(shipper: String, phone: PhoneEntity): Response<CheckRegister> {
        return customerAPI.checkRegister(shipper, phone)
    }
    override suspend fun getFillials(shipper: String): Response<FillialsResponse> {
        return customerAPI.getFillials(shipper)
    }

    override suspend fun getProfile(token: String): Response<ProfileResponse> {
        return customerAPI.getProfile(token)
    }

    override suspend fun getOrderHistory(
        token: String,
        userId: String
    ): Response<OrderHistoryResponse> {
        return customerAPI.getOrderHistory(token, userId)
    }

    override suspend fun getProductDetail(
        token: String,
        productId: String
    ): Response<ProductDetailResponse> {
        return customerAPI.getProductDetail(token, productId)
    }

    override suspend fun getProductSuggestion(
        token: String,
        productId: String
    ): Response<ProductSuggestionResponse> {
        return customerAPI.getProductSuggestion(token, productId)
    }

    override suspend fun register(shipper: String, phone: PhoneEntity): Response<RegisterResponse> {
        return customerAPI.register(shipper, phone)
    }
    override suspend fun login(shipper: String, phone: PhoneEntity): Response<RegisterResponse> {
        return customerAPI.login(shipper, phone)
    }
    override suspend fun registerConfirm(
        shipper: String,
        entity: RegisterConfirmEntity
    ): Response<RegisterConfirmResponse> {
        return customerAPI.registerConfirm(shipper,"website", entity)
    }

    override suspend fun loginConfirm(
        shipper: String,
        entity: RegisterConfirmEntity
    ): Response<RegisterConfirmResponse> {
        return customerAPI.loginConfirm(shipper,"website", entity)
    }

    override suspend fun updateUser(
        userId: String,
        token: String,
        entity: UpdateEntity
    ): Response<UpdateResponse> {
        return customerAPI.updateUser(userId, token, entity)
    }

    override suspend fun getAboutOrder(
        shipper: String,
        userId: String,
        token: String
    ): Response<AboutOrderResponse> {
        return customerAPI.getAboutOrder(shipper, userId, token)
    }
}