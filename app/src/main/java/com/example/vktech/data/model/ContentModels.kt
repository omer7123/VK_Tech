package com.example.vktech.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContentModel(
    val hits: List<VideoInfoModel>
)

@Serializable
data class VideoInfoModel(
    val id: Int,
    val views: Int,
    val likes: Int,
    val duration: Int,
    val tags: String,
    val videos: VideoSizeModel
)

@Serializable
data class VideoSizeModel(
    val medium: VideoModel
)

@Serializable
data class VideoModel(
    val url: String,
    val thumbnail: String,
)