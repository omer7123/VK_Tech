package com.example.vktech.di

import android.content.Context
import androidx.room.Room
import com.example.vktech.data.core.LiveNetworkMonitor
import com.example.vktech.data.core.NetworkMonitor
import com.example.vktech.data.local.room.AppDatabase
import com.example.vktech.data.local.room.ContentDao
import com.example.vktech.data.remote.interceptors.ApiKeyInterceptor
import com.example.vktech.data.remote.ContentApi
import com.example.vktech.data.remote.interceptors.NetworkMonitorInterceptor
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

    @Provides
    fun provideNetworkMonitor(
        appContext: Context
    ): NetworkMonitor{
        return LiveNetworkMonitor(appContext)
    }

    @Provides
    fun provideNetworkMonitorInterceptor(
        liveNetworkMonitor: NetworkMonitor,
    ): NetworkMonitorInterceptor {
        return NetworkMonitorInterceptor(liveNetworkMonitor)
    }

    @Reusable
    @Provides
    fun provideOkHttpClient(
        networkMonitorInterceptor: NetworkMonitorInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(networkMonitorInterceptor)
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

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "content_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideContentDao(appDatabase: AppDatabase): ContentDao {
        return appDatabase.contentDao()
    }
}