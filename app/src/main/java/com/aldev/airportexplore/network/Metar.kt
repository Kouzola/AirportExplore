package com.aldev.airportexplore.network

import com.squareup.moshi.Json

data class Metar(
    @Json(name = "results") val result: Int,
    @Json(name = "data") val metarData: List<String>
)
