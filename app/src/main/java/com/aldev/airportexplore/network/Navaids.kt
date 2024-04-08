package com.aldev.airportexplore.network

import com.squareup.moshi.Json

data class Navaids (
    @Json(name = "ident") val ident: String,
    @Json(name = "type") val type: String,
    @Json(name = "frequency_khz") val freq: String,
    @Json(name = "name") val name: String
)

