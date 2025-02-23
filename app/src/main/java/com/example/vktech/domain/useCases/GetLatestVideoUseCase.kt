package com.example.vktech.domain.useCases

import com.example.vktech.data.core.result.Resource
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.repository.VideoContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestVideoUseCase @Inject constructor(private val repository: VideoContentRepository) {
    suspend operator fun invoke(): Flow<Resource<ContentEntity>> = repository.getLatestVideo()
}