package com.example.vktech.data.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class LiveNetworkMonitor @Inject constructor(
    private val context: Context
):  NetworkMonitor {

    override fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        val isVpn = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        val isWifi = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        val isCellular = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)

        val hasInternet = ((isWifi || isCellular) && isVpn)

        return hasInternet
    }
}

interface NetworkMonitor {
    fun isConnected():Boolean
}