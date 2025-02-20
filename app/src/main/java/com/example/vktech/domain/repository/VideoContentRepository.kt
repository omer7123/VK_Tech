package com.example.vktech.domain.repository

import kotlinx.coroutines.flow.Flow

interface VideoContentRepository {
    suspend fun getMostPopularVideo(): Flow<String>
}