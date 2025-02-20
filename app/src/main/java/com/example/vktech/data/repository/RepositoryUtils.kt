package com.example.vktech.data.repository

import com.example.vktech.data.core.result.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <M, E> Flow<Resource<M>>.checkResource(
    crossinline transform: (M) -> E
): Flow<Resource<E>> {
    return this.map {
        when (it) {
            is Resource.Error -> {
                Resource.Error(
                    msg = it.msg.toString(),
                    responseCode = it.responseCode
                )
            }
            Resource.Loading -> Resource.Loading
            is Resource.Success -> {
                Resource.Success(transform(it.data)!!)
            }
        }
    }
}