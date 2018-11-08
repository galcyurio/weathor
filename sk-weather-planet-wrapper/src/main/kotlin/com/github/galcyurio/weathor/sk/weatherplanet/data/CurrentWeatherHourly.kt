package com.github.galcyurio.weathor.sk.weatherplanet.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.*
import com.github.galcyurio.weathor.sk.weatherplanet.support.CurrentWeatherHourlyDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.support.LightningDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.support.SkTimeDeserializer
import java.util.*

/**
 * 1시간 단위 현재날씨 정보
 * @author galcyurio
 */
@JsonDeserialize(using = CurrentWeatherHourlyDeserializer::class)
data class CurrentWeatherHourly(
    val elements: List<Element>,
    val common: Common,
    val result: Result
) {
    val firstElement = elements[0]

    data class Element(
        /** 격자 정보 */
        val grid: Grid,

        /** 바람 정보 */
        val wind: Wind,

        /** 강수 정보 */
        val precipitation: Precipitation,

        /** 하늘상태 정보 */
        val sky: Sky,

        /** 기온 정보 */
        val temperature: Temperature,

        /** 상대 습도 (%) */
        val humidity: String,

        /** 낙뢰유무 (해당 격자 내) */
        @JsonDeserialize(using = LightningDeserializer::class)
        val lightning: Boolean,

        /** 발표 시간 */
        @JsonDeserialize(using = SkTimeDeserializer::class)
        @JsonProperty("timeRelease")
        val releaseTime: Date,

        /** 일출 시간 */
        @JsonDeserialize(using = SkTimeDeserializer::class)
        val sunRiseTime: Date,

        /** 일몰 시간 */
        @JsonDeserialize(using = SkTimeDeserializer::class)
        val sunSetTime: Date
    )
}