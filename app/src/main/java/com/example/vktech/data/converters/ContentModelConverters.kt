package com.example.vktech.data.converters

import com.example.vktech.data.model.ContentModel
import com.example.vktech.data.model.VideoInfoModel
import com.example.vktech.data.model.VideoModel
import com.example.vktech.data.model.VideoSizeModel
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.entity.VideoEntity
import com.example.vktech.domain.entity.VideoInfoEntity

fun ContentModel.toEntity(): ContentEntity {
    return ContentEntity(
        hits = hits.map {videoModel ->
            videoModel.toEntity()
        }
    )
}

fun ContentModel.toVideoInfoEntity(): VideoInfoEntity{
    return hits[0].toEntity()
}

fun VideoInfoModel.toEntity(): VideoInfoEntity{
    return VideoInfoEntity(
        id = id,
        views = views,
        likes = likes,
        duration = duration,
        tag = tags.substringBefore(","),
        video = videos.toEntity()
    )
}

fun VideoInfoEntity.toModel(): VideoInfoModel{
    return VideoInfoModel(
        id = id,
        views = views,
        likes = likes,
        duration = duration,
        tags = tag,
        videos = VideoSizeModel(video.toModel())
    )
}

private fun VideoEntity.toModel(): VideoModel{
    return VideoModel(
        url,
        thumbnail
    )
}
private fun VideoSizeModel.toEntity() = VideoEntity(
    url = this.medium.url,
    thumbnail = this.medium.thumbnail,
)

