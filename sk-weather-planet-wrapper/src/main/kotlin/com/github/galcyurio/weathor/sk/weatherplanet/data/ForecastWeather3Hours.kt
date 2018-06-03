package com.github.galcyurio.weathor.sk.weatherplanet.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.*
import com.github.galcyurio.weathor.sk.weatherplanet.support.SkTimeDeserializer
import java.util.*

/**
 * 1시간 간격으로 매일 24회, 5Km 격자 단위로 4시간까지의 초단기예보 정보
 * @author galcyurio
 */
class ForecastWeather3Hours(
    val elements: List<Element>,
    val common: Common,
    val result: Result
) {

    data class Element(
        val grid: Grid,
        val wind: WindCollection,
        val precipitation: PrecipitationCollection,
        val sky: SkyCollection,
        val temperature: Temperature,
        val humidity: Humidity,
        val lightning: LightningCollection,

        @JsonDeserialize(using = SkTimeDeserializer::class)
        @JsonProperty("timeRelease")
        val releaseTime: Date
    )

    /** 바람정보 */
    data class WindCollection(
        /** 풍향, 풍속 (발표시간+1시간) */
        val after1hour: Wind,

        /** 풍향, 풍속 (발표시간+2시간) */
        val after2hour: Wind,

        /** 풍향, 풍속 (발표시간+3시간) */
        val after3hour: Wind,

        /** 풍향, 풍속 (발표시간+4시간) */
        val after4hour: Wind? = null
    )

    /** 강수정보 */
    data class PrecipitationCollection(
        /** 강수정보 (발표시간+1시간) */
        val after1hour: Precipitation,

        /** 강수정보 (발표시간+2시간) */
        val after2hour: Precipitation,

        /** 강수정보 (발표시간+3시간) */
        val after3hour: Precipitation,

        /** 강수정보 (발표시간+4시간) */
        val after4hour: Precipitation? = null
    )

    /** 하늘상태 정보 */
    data class SkyCollection(
        /** 하늘상태 (발표시간+1시간) */
        val after1hour: Sky,

        /** 하늘상태 (발표시간+2시간) */
        val after2hour: Sky,

        /** 하늘상태 (발표시간+3시간) */
        val after3hour: Sky,

        /** 하늘상태 (발표시간+4시간) */
        val after4hour: Sky? = null
    )

    /** 기온 정보 */
    data class Temperature(
        /** 기온 (발표시간+1시간) */
        val after1hour: Float,

        /** 기온 (발표시간+2시간) */
        val after2hour: Float,

        /** 기온 (발표시간+3시간) */
        val after3hour: Float,

        /** 기온 (발표시간+4시간) */
        val after4hour: Float? = null
    )

    /** 습도 정보 */
    data class Humidity(
        /** 상대습도 (발표시간+1시간) */
        val after1hour: Float,

        /** 상대습도 (발표시간+2시간) */
        val after2hour: Float,

        /** 상대습도 (발표시간+3시간) */
        val after3hour: Float,

        /** 상대습도 (발표시간+4시간) */
        val after4hour: Float? = null
    )

    /** 낙뢰 정보 (관측소 5km 반경 내) */
    data class LightningCollection(
        /** 낙뢰 확률 (발표시간+1시간) */
        val after1hour: Boolean,

        /** 낙뢰 확률 (발표시간+1시간) */
        val after2hour: Float,

        /** 낙뢰 확률 (발표시간+1시간) */
        val after3hour: Float,

        /** 낙뢰 확률 (발표시간+1시간) */
        val after4hour: Float? = null
    )
}