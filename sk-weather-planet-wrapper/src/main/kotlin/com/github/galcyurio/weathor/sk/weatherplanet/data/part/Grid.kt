package com.github.galcyurio.weathor.sk.weatherplanet.data.part

/**
 * 격자 정보
 * @author galcyurio
 */
data class Grid(
    val longitude: Double,
    val latitude: Double,
    val county: String,
    val village: String,
    val city: String
)