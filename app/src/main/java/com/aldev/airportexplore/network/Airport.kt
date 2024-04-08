package com.aldev.airportexplore.network

import com.squareup.moshi.Json

data class Airport(
    @Json(name="name") val name: String,
    @Json(name="gps_code") val icao: String,
    @Json(name ="iata_code") val iata: String,
    @Json(name = "elevation_ft") val elevation: String,
    @Json(name = "runways") val runways: List<Runway>,
    @Json(name = "latitude_deg") val latitude: Float,
    @Json(name = "longitude_deg") val longitude: Float,
    @Json(name = "navaids") val navaids: List<Navaids>?
)
