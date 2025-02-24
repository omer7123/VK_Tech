package com.example.vktech.data.local.room

import com.example.vktech.data.core.result.Resource
import com.example.vktech.data.model.VideoInfoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val db: AppDatabase) : LocalDataSource {
    override suspend fun getAll(): Flow<Resource<List<VideoInfoModel>>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(db.contentDao().getAll()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message, responseCode = 2))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun saveAll(content: List<VideoInfoModel>) = flow{
        emit(Resource.Loading)
        try {
            emit(Resource.Success(db.contentDao().clearAndInsertAll(content)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message, responseCode = 2))
        }
    }.flowOn(Dispatchers.IO)

}