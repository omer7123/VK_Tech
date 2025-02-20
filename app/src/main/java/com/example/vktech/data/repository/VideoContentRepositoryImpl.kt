package com.example.vktech.data.repository

import com.example.vktech.data.converters.toEntity
import com.example.vktech.data.core.result.Resource
import com.example.vktech.data.remote.RemoteDataSource
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.repository.VideoContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoContentRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): VideoContentRepository {
    override suspend fun getMostPopularVideo(): Flow<Resource<ContentEntity>> {
        return remoteDataSource.getMostPopularVideo().checkResource {
            it.toEntity()
        }
    }
}