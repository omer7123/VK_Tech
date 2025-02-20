package com.example.vktech.di

import com.example.vktech.data.remote.RemoteDataSource
import com.example.vktech.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource
}