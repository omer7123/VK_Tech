package com.example.vktech.data.remote

import com.example.vktech.data.model.ContentModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContentApi {
    @GET("videos/?order=latest")
    suspend fun getLatestVideos(): Response<ContentModel>

    @GET("videos/")
    suspend fun getVideoById(
        @Query("id") videoId: Int
    ): Response<ContentModel>
}