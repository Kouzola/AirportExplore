package com.aldev.airportexplore.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NetworkConnectivity(context: Context) {

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected



    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private val connectivityManager = context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

    init{
        val networkCallback = object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _isConnected.value = true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                _isConnected.value = false
            }
        }
        connectivityManager.requestNetwork(networkRequest,networkCallback)
    }


}