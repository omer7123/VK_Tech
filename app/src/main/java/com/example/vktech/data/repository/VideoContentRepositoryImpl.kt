package com.example.vktech.data.repository

import com.example.vktech.data.converters.toEntity
import com.example.vktech.data.converters.toVideoInfoEntity
import com.example.vktech.data.core.result.Resource
import com.example.vktech.data.remote.RemoteDataSource
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.entity.VideoInfoEntity
import com.example.vktech.domain.repository.VideoContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoContentRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): VideoContentRepository {
    override suspend fun getLatestVideo(): Flow<Resource<ContentEntity>> {
        return remoteDataSource.getLatestVideo().checkResource {
            it.toEntity()
        }
    }

    override suspend fun getVideoById(videoId: Int): Flow<Resource<VideoInfoEntity>> {
        return remoteDataSource.getVideoById(videoId).checkResource {
            it.toVideoInfoEntity()
        }
    }
}