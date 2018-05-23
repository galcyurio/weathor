package com.github.galcyurio.weathor.sk.weatherplanet.data

/**
 * 기압정보
 * @author galcyurio
 */
data class Pressure(
    /** 현지기압(surface pressure) */
    val surface: Float,

    /** 해면기압(sea level pressure) */
    val seaLevel: Float
)