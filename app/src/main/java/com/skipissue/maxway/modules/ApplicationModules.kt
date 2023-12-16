package com.skipissue.maxway.modules

import com.skipissue.maxway.data.api.CustomerAPI
import com.skipissue.maxway.data.api.MaxWayAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModules {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://fastfood.urinboev.uz/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideMaxWayApi(okHttpClient: OkHttpClient): MaxWayAPI {
        return Retrofit.Builder()
            .baseUrl("http://fastfood.urinboev.uz/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }
    @Provides
    @Singleton
    fun provideCustomerApi(okHttpClient: OkHttpClient): CustomerAPI {
        return Retrofit.Builder()
            .baseUrl("https://customer.api.delever.uz/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }
}