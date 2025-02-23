package com.example.vktech.data.converters

import com.example.vktech.data.model.ContentModel
import com.example.vktech.data.model.VideoInfoModel
import com.example.vktech.data.model.VideoModel
import com.example.vktech.data.model.VideoSizeModel
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.entity.VideoEntity
import com.example.vktech.domain.entity.VideoInfoEntity
import com.example.vktech.domain.entity.VideoSizeEntity

fun ContentModel.toEntity(): ContentEntity{
    return ContentEntity(
        total = total,
        totalHits = totalHits,
        hits = hits.map {videoModel ->
            videoModel.toEntity()
        }
    )
}

private fun VideoInfoModel.toEntity(): VideoInfoEntity{
    return VideoInfoEntity(
        id = id,
        pageURL = pageURL,
        type = type,
        duration = duration,
        tag = tags.substringBefore(","),
        videos = videos.toEntity()
    )
}

private fun VideoSizeModel.toEntity() = VideoSizeEntity(
    medium = medium.toEntity()
)

private fun VideoModel.toEntity() = VideoEntity(
    url = url,
    thumbnail = thumbnail
)
