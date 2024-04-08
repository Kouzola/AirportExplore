package com.aldev.airportexplore.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldev.airportexplore.BuildConfig
import com.aldev.airportexplore.network.Airport
import com.aldev.airportexplore.network.ApiClient
import com.aldev.airportexplore.network.Metar
import com.aldev.airportexplore.network.NetworkConnectivity
import kotlinx.coroutines.launch

enum class ApiStatus {LOADING, ERROR, DONE}

private const val API_TOKEN = BuildConfig.API_TOKEN

class AirportViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status = _status

    private val _airport = MutableLiveData<Airport>()
    val airport: LiveData<Airport> = _airport

    private val _errorCode = MutableLiveData<String>()
    val errorCode: LiveData<String> = _errorCode

    private val _isInternetAvailable = MutableLiveData<Boolean>()
    val isInternetAvailable: LiveData<Boolean> = _isInternetAvailable

    private val _metar = MutableLiveData<Metar>()
    val metar: LiveData<Metar> = _metar




    fun searchAirport(icao: String){
        if(icao.trim().isNotEmpty()) {
            viewModelScope.launch {
                _status.value = ApiStatus.LOADING
                try {
                    _airport.value = ApiClient.apiService.getAirport(icao, API_TOKEN)
                    _status.value = ApiStatus.DONE
                } catch (e: Exception) {
                    Log.d("AirportViewModel", e.message.toString())
                    _errorCode.value = e.message.toString()
                    _status.value = ApiStatus.ERROR
                }
            }
        }
    }

    fun searchMetar(icao: String){
        if(icao.trim().isNotEmpty()) {
            viewModelScope.launch {
                try {
                    _metar.value = ApiClient.apiService.getMetar(icao)
                } catch (e: Exception) {
                    Log.d("AirportViewModel", e.message.toString())
                }
            }
        }
    }

    fun checkInternetConnection(context: Context){
        val networkConnectivity = NetworkConnectivity(context)
        viewModelScope.launch{
            networkConnectivity.isConnected.collect{
                _isInternetAvailable.value = it
            }
        }
    }

}