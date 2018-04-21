package com.github.galcyurio.weathor.sk.weather.data

/**
 * @author galcyurio
 */
data class RawSkWeatherStatus(
    val weatherStatusCode: Int,
    val weatherStatusDescription: String,
    val weatherModifyCode: Int,
    val weatherModifyDescription: String
)