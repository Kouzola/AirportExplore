package com.aldev.airportexplore.network


import com.aldev.airportexplore.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface AiportAPI {

    @GET("airport/{ICAO}")
    suspend fun getAirport(@Path("ICAO") icao: String, @Query("apiToken") apiToken: String): Airport

    @Headers("X-API-Key: ${BuildConfig.API_METAR}")
    @GET
    suspend fun getMetar(@Url metarBaseUrl: String): Metar

}