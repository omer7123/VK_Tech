package com.example.vktech.di

import com.example.vktech.data.local.room.LocalDataSource
import com.example.vktech.data.local.room.LocalDataSourceImpl
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

    @Binds
    @Singleton
    fun bindLoanRoomDataSource(impl: LocalDataSourceImpl): LocalDataSource
}