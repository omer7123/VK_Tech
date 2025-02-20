package com.example.vktech.di

import com.example.vktech.data.repository.VideoContentRepositoryImpl
import com.example.vktech.domain.repository.VideoContentRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Binds
    @Singleton
    fun bindVideoContentRepository(impl: VideoContentRepositoryImpl): VideoContentRepository
}