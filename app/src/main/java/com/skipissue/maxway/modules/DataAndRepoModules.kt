package com.skipissue.maxway.modules

import com.skipissue.maxway.data.datasource.DataBaseDataSource
import com.skipissue.maxway.data.datasource.DataBaseDataSourceImpl
import com.skipissue.maxway.data.datasource.ProductsDataSource
import com.skipissue.maxway.data.datasource.ProductsDataSourceImpl
import com.skipissue.maxway.data.repos.DatabaseRepository
import com.skipissue.maxway.data.repos.DatabaseRepositoryImpl
import com.skipissue.maxway.data.repos.ProductsRepository
import com.skipissue.maxway.data.repos.ProductsRepositoryImpl
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.data.settings.SettingsImpl
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
    @Binds
    fun bindDBRepo(repositoryImpl: DatabaseRepositoryImpl): DatabaseRepository
    @Binds
    fun bindDBDataSource(repositoryImpl: DataBaseDataSourceImpl): DataBaseDataSource
    @Binds
    fun bindSettings(settingsImpl: SettingsImpl): Settings
}