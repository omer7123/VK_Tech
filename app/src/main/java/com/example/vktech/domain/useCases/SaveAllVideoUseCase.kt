package com.example.vktech.domain.useCases

import com.example.vktech.data.core.result.Resource
import com.example.vktech.domain.entity.VideoInfoEntity
import com.example.vktech.domain.repository.VideoContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveAllVideoUseCase @Inject constructor(private val repository: VideoContentRepository) {

    suspend operator fun invoke(content: List<VideoInfoEntity>): Flow<Resource<Unit>> =
        repository.deleteAndSaveAllVideo(content)
}