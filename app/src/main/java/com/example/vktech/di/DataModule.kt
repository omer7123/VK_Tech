package com.example.vktech.di

import com.example.vktech.data.remote.ApiKeyInterceptor
import com.example.vktech.data.remote.ContentApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideApiUrl(impl: ApiUrlProviderImpl): ApiUrlProvider = impl

    @Reusable
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Reusable
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }
    @Reusable
    @Provides
    fun provideRetrofitClient(json: Json, apiUrlProvider: ApiUrlProvider, okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(apiUrlProvider.url)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideContentApi(retrofit: Retrofit): ContentApi {
        return retrofit.create(ContentApi::class.java)
    }
}