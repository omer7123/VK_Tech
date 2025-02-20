package com.example.vktech.domain.entity

data class ContentEntity(
    val total: Int,
    val totalHits: Int,
    val hits: List<VideoEntity>
)

data class VideoEntity(
    val id: Int,
    val pageURL: String,
    val type: String
)