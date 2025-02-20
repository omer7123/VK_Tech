package com.example.vktech.data.core.result

sealed class Resource<out T> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error<out T : Any>(val msg: String?, val responseCode: Int?) :
        Resource<T>()
    data object Loading: Resource<Nothing>()
}
