package com.example.vktech.data.remote.interceptors

import android.util.Log
import com.example.vktech.data.core.NetworkMonitor
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NetworkMonitorInterceptor @Inject constructor(
    private val liveNetworkMonitor: NetworkMonitor
): Interceptor {
    @Throws(NoNetworkException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if(liveNetworkMonitor.isConnected()){
            return chain.proceed(request)
        }else{
            throw NoNetworkException("Network Error")
        }
    }
}

class NoNetworkException(message:String): IOException(message)