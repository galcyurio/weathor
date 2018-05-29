package com.github.galcyurio.weathor.sk.weatherplanet.data.part

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 바람 정보
 * @author galcyurio
 */
data class Wind(
    /** 풍향 (degree) */
    @JsonProperty("wdir")
    val direction: Float,

    /** 풍속 (m/s) */
    @JsonProperty("wspd")
    val speed: Float
)