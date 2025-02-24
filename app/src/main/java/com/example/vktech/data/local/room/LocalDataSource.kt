package com.example.vktech.data.local.room

import com.example.vktech.data.core.result.Resource
import com.example.vktech.data.model.VideoInfoModel
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    suspend fun getAll(): Flow<Resource<List<VideoInfoModel>>>
    suspend fun saveAll(content: List<VideoInfoModel>): Flow<Resource<Unit>>

}
