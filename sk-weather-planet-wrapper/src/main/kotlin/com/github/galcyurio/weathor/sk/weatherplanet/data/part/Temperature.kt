package com.github.galcyurio.weathor.sk.weatherplanet.data.part

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 기온 정보
 * @author galcyurio
 */
data class Temperature(
    /** 현재 기온 */
    @JsonProperty("tc")
    val current: Float,

    /** 오늘의 최고기온 */
    @JsonProperty("tmax")
    val max: Float,

    /** 오늘의 최저기온 */
    @JsonProperty("tmin")
    val min: Float
)