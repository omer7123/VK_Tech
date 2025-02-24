package com.example.vktech.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.vktech.data.converters.RoomTypeConverters
import com.example.vktech.data.model.ContentModel
import com.example.vktech.data.model.VideoInfoModel
import com.example.vktech.domain.entity.ContentEntity
import com.example.vktech.domain.entity.VideoInfoEntity

@Database(entities = [VideoInfoModel::class], version = 1)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao
}