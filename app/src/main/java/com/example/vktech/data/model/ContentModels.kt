package com.example.vktech.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContentModel(
    val total: Int,
    val totalHits: Int,
    val hits: List<VideoModel>
)

@Serializable
data class VideoModel(
    val id: Int,
    val pageURL: String,
    val type: String
)