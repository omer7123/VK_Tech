package com.example.vktech.domain.repository

import com.example.vktech.data.core.result.Resource
import com.example.vktech.data.model.VideoInfoModel
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.entity.VideoInfoEntity
import kotlinx.coroutines.flow.Flow

interface VideoContentRepository {
    suspend fun getLatestVideo(): Flow<Resource<ContentEntity>>
    suspend fun getVideoById(videoId: Int): Flow<Resource<VideoInfoEntity>>
    suspend fun getAllVideo(): Flow<Resource<List<VideoInfoEntity>>>
    suspend fun deleteAndSaveAllVideo(content: List<VideoInfoEntity>): Flow<Resource<Unit>>
}