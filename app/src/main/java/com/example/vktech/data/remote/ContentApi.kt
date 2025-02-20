package com.example.vktech.data.remote

import com.example.vktech.data.model.ContentModel
import retrofit2.Response
import retrofit2.http.GET

interface ContentApi {
    @GET("videos/")
    suspend fun getMostPopularVideos(): Response<ContentModel>
}