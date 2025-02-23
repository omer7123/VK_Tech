package com.example.vktech.data.remote

import com.example.vktech.data.core.result.Resource
import com.example.vktech.data.model.ContentModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getLatestVideo(): Flow<Resource<ContentModel>>
}