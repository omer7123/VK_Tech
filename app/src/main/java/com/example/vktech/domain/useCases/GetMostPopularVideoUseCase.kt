package com.example.vktech.domain.useCases

import com.example.vktech.domain.repository.VideoContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMostPopularVideoUseCase @Inject constructor(private val repository: VideoContentRepository) {

    suspend operator fun invoke(): Flow<String> = repository.getMostPopularVideo()
}