package com.example.vktech.domain.entity

data class ContentEntity(
    val total: Int,
    val totalHits: Int,
    val hits: List<VideoInfoEntity>
)

data class VideoInfoEntity(
    val id: Int,
    val pageURL: String,
    val type: String,
    val duration: Int,
    val tag: String,
    val videos: VideoSizeEntity
)

data class VideoSizeEntity(
    val medium: VideoEntity
)

data class VideoEntity(
    val url: String,
    val thumbnail: String,
)