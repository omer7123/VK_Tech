package com.example.vktech.data.remote

import com.example.vktech.data.core.BaseDataSource
import com.example.vktech.data.core.result.Resource
import com.example.vktech.data.model.ContentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: ContentApi
): RemoteDataSource, BaseDataSource() {

    override suspend fun getLatestVideo(): Flow<Resource<ContentModel>> = flow {
        emit(Resource.Loading)
        val result = getResult { api.getLatestVideos() }
        emit(result)
    }.flowOn(Dispatchers.IO)

    override suspend fun getVideoById(videoId: Int): Flow<Resource<ContentModel>> = flow<Resource<ContentModel>> {
        emit(Resource.Loading)
        val result = getResult { api.getVideoById(videoId) }
        emit(result)
    }.flowOn(Dispatchers.IO)
}