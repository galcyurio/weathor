package com.github.galcyurio.weathor.sk.weatherplanet.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.*
import com.github.galcyurio.weathor.sk.weatherplanet.support.*
import java.util.*

@JsonDeserialize(using = ForecastWeather3DaysDeserializer::class)
data class ForecastWeather3Days(
    val elements: List<Element>,
    val common: Common,
    val result: Result
) {
    val firstElement = elements[0]

    @JsonDeserialize(using = Element3DaysDeserializer::class)
    data class Element(
        val grid: Grid,
        val releaseTime: Date,

        val wind: WindCollection,
        val precipitation: PrecipitationProbabilityCollection,
        val sky: SkyCollection,
        val temperature: TemperatureCollection,
        val humidity: HumidityCollection,
        val rain: RainCollection,
        val snow: SnowCollection,
        val temperatureMinMax: TemperatureMinMaxCollection
    )

    /* ============================= 3시간 ============================= */

    /** 바람 정보 */
    @JsonDeserialize(using = WindCollection3DaysDeserializer::class)
    data class WindCollection(
        /**
         * - key: 발표시간
         * - value: 바람 정보
         * */
        val data: Map<Int, Wind>,
        val after4hour: Wind? = null,
        val after7hour: Wind? = null,
        val after10hour: Wind? = null,
        val after13hour: Wind? = null,
        val after16hour: Wind? = null,
        val after19hour: Wind? = null,
        val after22hour: Wind? = null,
        val after25hour: Wind? = null,
        val after28hour: Wind? = null,
        val after31hour: Wind? = null,
        val after34hour: Wind? = null,
        val after37hour: Wind? = null,
        val after40hour: Wind? = null,
        val after43hour: Wind? = null,
        val after46hour: Wind? = null,
        val after49hour: Wind? = null,
        val after52hour: Wind? = null,
        val after55hour: Wind? = null,
        val after58hour: Wind? = null,
        val after61hour: Wind? = null,
        val after64hour: Wind? = null,
        val after67hour: Wind? = null
    )

    /** 강수 확률 정보 */
    @JsonDeserialize(using = PrecipitationProbabilityCollection3DaysDeserializer::class)
    data class PrecipitationProbabilityCollection(
        /**
         * - key: 발표시간
         * - value: 강수 확률 정보
         */
        val data: Map<Int, PrecipitationProbability>,
        val after4hour: PrecipitationProbability? = null,
        val after7hour: PrecipitationProbability? = null,
        val after10hour: PrecipitationProbability? = null,
        val after13hour: PrecipitationProbability? = null,
        val after16hour: PrecipitationProbability? = null,
        val after19hour: PrecipitationProbability? = null,
        val after22hour: PrecipitationProbability? = null,
        val after25hour: PrecipitationProbability? = null,
        val after28hour: PrecipitationProbability? = null,
        val after31hour: PrecipitationProbability? = null,
        val after34hour: PrecipitationProbability? = null,
        val after37hour: PrecipitationProbability? = null,
        val after40hour: PrecipitationProbability? = null,
        val after43hour: PrecipitationProbability? = null,
        val after46hour: PrecipitationProbability? = null,
        val after49hour: PrecipitationProbability? = null,
        val after52hour: PrecipitationProbability? = null,
        val after55hour: PrecipitationProbability? = null,
        val after58hour: PrecipitationProbability? = null,
        val after61hour: PrecipitationProbability? = null,
        val after64hour: PrecipitationProbability? = null,
        val after67hour: PrecipitationProbability? = null
    )

    data class PrecipitationProbability(
        val type: Precipitation.Type,
        val percentage: Float
    )

    /** 하늘상태 정보 */
    @JsonDeserialize(using = SkyCollection3DaysDeserializer::class)
    data class SkyCollection(
        /**
         * - key: 발표시간
         * - value: 하늘상태 정보
         */
        val data: Map<Int, Sky>,
        val after4hour: Sky? = null,
        val after7hour: Sky? = null,
        val after10hour: Sky? = null,
        val after13hour: Sky? = null,
        val after16hour: Sky? = null,
        val after19hour: Sky? = null,
        val after22hour: Sky? = null,
        val after25hour: Sky? = null,
        val after28hour: Sky? = null,
        val after31hour: Sky? = null,
        val after34hour: Sky? = null,
        val after37hour: Sky? = null,
        val after40hour: Sky? = null,
        val after43hour: Sky? = null,
        val after46hour: Sky? = null,
        val after49hour: Sky? = null,
        val after52hour: Sky? = null,
        val after55hour: Sky? = null,
        val after58hour: Sky? = null,
        val after61hour: Sky? = null,
        val after64hour: Sky? = null,
        val after67hour: Sky? = null
    )

    /** 기온 정보 */
    @JsonDeserialize(using = TemperatureCollection3DaysDeserializer::class)
    data class TemperatureCollection(
        /**
         * - key: 발표시간
         * - value: 기온정보
         */
        val data: Map<Int, Float>,
        val after4hour: Float? = null,
        val after7hour: Float? = null,
        val after10hour: Float? = null,
        val after13hour: Float? = null,
        val after16hour: Float? = null,
        val after19hour: Float? = null,
        val after22hour: Float? = null,
        val after25hour: Float? = null,
        val after28hour: Float? = null,
        val after31hour: Float? = null,
        val after34hour: Float? = null,
        val after37hour: Float? = null,
        val after40hour: Float? = null,
        val after43hour: Float? = null,
        val after46hour: Float? = null,
        val after49hour: Float? = null,
        val after52hour: Float? = null,
        val after55hour: Float? = null,
        val after58hour: Float? = null,
        val after61hour: Float? = null,
        val after64hour: Float? = null,
        val after67hour: Float? = null
    )

    /** 습도 정보 */
    @JsonDeserialize(using = HumidityCollection3DaysDeserializer::class)
    data class HumidityCollection(
        /**
         * - key: 발표시간
         * - value: 습도 정보
         */
        val data: Map<Int, Float>,
        val after4hour: Float? = null,
        val after7hour: Float? = null,
        val after10hour: Float? = null,
        val after13hour: Float? = null,
        val after16hour: Float? = null,
        val after19hour: Float? = null,
        val after22hour: Float? = null,
        val after25hour: Float? = null,
        val after28hour: Float? = null,
        val after31hour: Float? = null,
        val after34hour: Float? = null,
        val after37hour: Float? = null,
        val after40hour: Float? = null,
        val after43hour: Float? = null,
        val after46hour: Float? = null,
        val after49hour: Float? = null,
        val after52hour: Float? = null,
        val after55hour: Float? = null,
        val after58hour: Float? = null,
        val after61hour: Float? = null,
        val after64hour: Float? = null,
        val after67hour: Float? = null
    )

    /* ============================= 6시간 ============================= */

    /** 누적 강우량 */
    @JsonDeserialize(using = RainCollection3DaysDeserializer::class)
    data class RainCollection(
        val data: Map<Int, Rain>,
        val after6hour: Rain? = null,
        val after12hour: Rain? = null,
        val after18hour: Rain? = null,
        val after24hour: Rain? = null,
        val after30hour: Rain? = null,
        val after36hour: Rain? = null,
        val after42hour: Rain? = null,
        val after48hour: Rain? = null,
        val after54hour: Rain? = null,
        val after60hour: Rain? = null,
        val after66hour: Rain? = null
    )

    /** 6시간 신 적설량 */
    @JsonDeserialize(using = SnowCollection3DaysDeserializer::class)
    data class SnowCollection(
        val data: Map<Int, Snow>,
        val after6hour: Snow? = null,
        val after12hour: Snow? = null,
        val after18hour: Snow? = null,
        val after24hour: Snow? = null,
        val after30hour: Snow? = null,
        val after36hour: Snow? = null,
        val after42hour: Snow? = null,
        val after48hour: Snow? = null,
        val after54hour: Snow? = null,
        val after60hour: Snow? = null,
        val after66hour: Snow? = null
    )

    enum class Rain {
        NONE,
        UNDER_1_MM,
        BETWEEN_1_4_MM,
        BETWEEN_5_9_MM,
        BETWEEN_10_19_MM,
        BETWEEN_20_39_MM,
        BETWEEN_40_69_MM,
        MORE_THAN_70_MM
    }

    enum class Snow {
        NONE,
        UNDER_1_CM,
        BETWEEN_1_4_CM,
        BETWEEN_5_9_CM,
        BETWEEN_10_19_CM,
        MORE_THAN_20_CM
    }

    /* ============================= 매일 ============================= */

    data class TemperatureMinMaxCollection(
        /** 일 최저기온(오늘) */
        @JsonProperty("tmin1day") val min1day: Float?,

        /** 일 최고기온(오늘) */
        @JsonProperty("tmax1day") val max1day: Float?,

        /** 일 최저기온(내일) */
        @JsonProperty("tmin2day") val min2day: Float?,

        /** 일 최고기온(내일) */
        @JsonProperty("tmax2day") val max2day: Float?,

        /** 일 최저기온(모레) */
        @JsonProperty("tmin3day") val min3day: Float?,

        /** 일 최고기온(모레) */
        @JsonProperty("tmax3day") val max3day: Float?
    )
}
