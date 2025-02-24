package com.example.vktech.domain.repository

import com.example.vktech.data.core.result.Resource
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.entity.VideoInfoEntity
import kotlinx.coroutines.flow.Flow

interface VideoContentRepository {
    suspend fun getLatestVideo(): Flow<Resource<ContentEntity>>
    suspend fun getVideoById(videoId: Int): Flow<Resource<VideoInfoEntity>>
}