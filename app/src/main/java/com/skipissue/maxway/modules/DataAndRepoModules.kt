package com.skipissue.maxway.modules

import com.skipissue.maxway.data.datasource.ProductsDataSource
import com.skipissue.maxway.data.datasource.ProductsDataSourceImpl
import com.skipissue.maxway.data.repos.ProductsRepository
import com.skipissue.maxway.data.repos.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataAndRepoModules {
    @Binds
    fun bindDataSource(dataSourceImpl: ProductsDataSourceImpl): ProductsDataSource
    @Binds
    fun bindRepo(repositoryImpl: ProductsRepositoryImpl): ProductsRepository
}