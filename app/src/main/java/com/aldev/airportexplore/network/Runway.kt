package com.aldev.airportexplore.network

import com.squareup.moshi.Json

data class Runway (
    @Json(name = "le_ident") val runwayNumber1: String,
    @Json(name = "he_ident") val runwayNumber2: String,
    @Json(name = "length_ft") val length: String,
    @Json(name = "lighted") val isLighted: String
)
