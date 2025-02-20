package com.example.vktech.data.converters

import com.example.vktech.data.model.ContentModel
import com.example.vktech.data.model.VideoModel
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.entity.VideoEntity

fun ContentModel.toEntity(): ContentEntity{
    return ContentEntity(
        total = total,
        totalHits = totalHits,
        hits = hits.map {videoModel ->
            videoModel.toEntity()
        }
    )
}

private fun VideoModel.toEntity(): VideoEntity{
    return VideoEntity(
        id = id,
        pageURL = pageURL,
        type = type,
    )
}