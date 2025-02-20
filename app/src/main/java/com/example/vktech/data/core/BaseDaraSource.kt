package com.example.vktech.data.core


import com.example.vktech.data.core.result.Resource
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
        } catch (e: Exception) {
            return Resource.Error(e.message ?: e.toString(),429)
        }

        return Resource.Error(null, 429)
    }
}