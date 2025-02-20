package com.example.vktech.data.repository

import com.example.vktech.domain.repository.VideoContentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VideoContentRepositoryImpl @Inject constructor(): VideoContentRepository {
    override suspend fun getMostPopularVideo(): Flow<String> = flow{
        emit("")
    }.flowOn(Dispatchers.IO)
}