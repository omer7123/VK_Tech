package com.example.vktech.data.converters

import androidx.room.TypeConverter
import com.example.vktech.data.model.VideoInfoModel
import com.example.vktech.data.model.VideoModel
import com.example.vktech.data.model.VideoSizeModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object RoomTypeConverters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromVideoInfoList(value: List<VideoInfoModel>): String = json.encodeToString(value)

    @TypeConverter
    fun toVideoInfoList(value: String): List<VideoInfoModel> = json.decodeFromString(value)

    @TypeConverter
    fun fromVideoSize(value: VideoSizeModel): String = json.encodeToString(value)

    @TypeConverter
    fun toVideoSize(value: String): VideoSizeModel = json.decodeFromString(value)

    @TypeConverter
    fun fromVideoModel(value: VideoModel): String = json.encodeToString(value)

    @TypeConverter
    fun toVideoModel(value: String): VideoModel = json.decodeFromString(value)
}