package com.example.vktech.data.core


import android.util.Log
import com.example.vktech.data.core.result.Resource
import com.example.vktech.data.remote.interceptors.NoNetworkException
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T: Any> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null || response.code() in 200..299) return Resource.Success(body!!)

            } else {
                return Resource.Error(response.message(), response.code())
            }

        }catch (noNetwork: NoNetworkException) {
            Log.e("BASE", "No internet connection", noNetwork) // Логируем исключение
            return Resource.Error(noNetwork.message ?: "No internet", 0)

        } catch (e: Exception) {
            Log.e("BASE", "Unexpected error", e) // Логируем другие ошибки
            return Resource.Error(e.message ?: "Unknown error", 429)
        }

        return Resource.Error(null, 429)
    }
}