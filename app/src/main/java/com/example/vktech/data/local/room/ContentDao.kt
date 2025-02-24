package com.example.vktech.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.vktech.data.model.VideoInfoModel

@Dao
interface ContentDao {

    @Query("SELECT * FROM videoinfomodel")
    suspend fun getAll(): List<VideoInfoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contents: List<VideoInfoModel>)

    @Query("DELETE FROM videoinfomodel")
    suspend fun clearAll()

    @Transaction
    suspend fun clearAndInsertAll(contents: List<VideoInfoModel>){
        clearAll()
        insertAll(contents)
    }
}