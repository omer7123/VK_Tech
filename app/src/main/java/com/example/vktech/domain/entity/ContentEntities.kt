package com.example.vktech.domain.entity

data class ContentEntity(
    val hits: List<VideoInfoEntity>
)

data class VideoInfoEntity(
    val id: Int,
    val duration: Int,
    val views: Int,
    val likes: Int,
    val tag: String,
    val video: VideoEntity
)

data class VideoEntity(
    val url: String,
    val thumbnail: String,
)