package com.example.vktech.domain.repository

import com.example.vktech.data.core.result.Resource
import com.example.vktech.domain.entity.ContentEntity
import kotlinx.coroutines.flow.Flow

interface VideoContentRepository {
    suspend fun getMostPopularVideo(): Flow<Resource<ContentEntity>>
}