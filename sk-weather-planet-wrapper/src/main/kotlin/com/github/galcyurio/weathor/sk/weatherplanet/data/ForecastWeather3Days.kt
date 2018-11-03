package com.github.galcyurio.weathor.sk.weatherplanet.data

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.*
import com.github.galcyurio.weathor.sk.weatherplanet.support.*
import java.util.*

data class ForecastWeather3Days(
    val elements: List<Element>,
    val common: Common,
    val result: Result
) {

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
        val after4hour: Wind,
        val after7hour: Wind,
        val after10hour: Wind,
        val after13hour: Wind,
        val after16hour: Wind,
        val after19hour: Wind,
        val after22hour: Wind,
        val after25hour: Wind,
        val after28hour: Wind,
        val after31hour: Wind,
        val after34hour: Wind,
        val after37hour: Wind,
        val after40hour: Wind,
        val after43hour: Wind,
        val after46hour: Wind,
        val after49hour: Wind,
        val after52hour: Wind,
        val after55hour: Wind,
        val after58hour: Wind,
        val after61hour: Wind,
        val after64hour: Wind,
        val after67hour: Wind?
    )

    /** 강수 확률 정보 */
    @JsonDeserialize(using = PrecipitationProbabilityCollection3DaysDeserializer::class)
    data class PrecipitationProbabilityCollection(
        /**
         * - key: 발표시간
         * - value: 강수 확률 정보
         */
        val data: Map<Int, PrecipitationProbability>,
        val after4hour: PrecipitationProbability,
        val after7hour: PrecipitationProbability,
        val after10hour: PrecipitationProbability,
        val after13hour: PrecipitationProbability,
        val after16hour: PrecipitationProbability,
        val after19hour: PrecipitationProbability,
        val after22hour: PrecipitationProbability,
        val after25hour: PrecipitationProbability,
        val after28hour: PrecipitationProbability,
        val after31hour: PrecipitationProbability,
        val after34hour: PrecipitationProbability,
        val after37hour: PrecipitationProbability,
        val after40hour: PrecipitationProbability,
        val after43hour: PrecipitationProbability,
        val after46hour: PrecipitationProbability,
        val after49hour: PrecipitationProbability,
        val after52hour: PrecipitationProbability,
        val after55hour: PrecipitationProbability,
        val after58hour: PrecipitationProbability,
        val after61hour: PrecipitationProbability,
        val after64hour: PrecipitationProbability,
        val after67hour: PrecipitationProbability?
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
        val after4hour: Sky,
        val after7hour: Sky,
        val after10hour: Sky,
        val after13hour: Sky,
        val after16hour: Sky,
        val after19hour: Sky,
        val after22hour: Sky,
        val after25hour: Sky,
        val after28hour: Sky,
        val after31hour: Sky,
        val after34hour: Sky,
        val after37hour: Sky,
        val after40hour: Sky,
        val after43hour: Sky,
        val after46hour: Sky,
        val after49hour: Sky,
        val after52hour: Sky,
        val after55hour: Sky,
        val after58hour: Sky,
        val after61hour: Sky,
        val after64hour: Sky,
        val after67hour: Sky?
    )

    /** 기온 정보 */
    @JsonDeserialize(using = TemperatureCollection3DaysDeserializer::class)
    data class TemperatureCollection(
        /**
         * - key: 발표시간
         * - value: 기온정보
         */
        val data: Map<Int, Float>,
        val after4hour: Float,
        val after7hour: Float,
        val after10hour: Float,
        val after13hour: Float,
        val after16hour: Float,
        val after19hour: Float,
        val after22hour: Float,
        val after25hour: Float,
        val after28hour: Float,
        val after31hour: Float,
        val after34hour: Float,
        val after37hour: Float,
        val after40hour: Float,
        val after43hour: Float,
        val after46hour: Float,
        val after49hour: Float,
        val after52hour: Float,
        val after55hour: Float,
        val after58hour: Float,
        val after61hour: Float,
        val after64hour: Float,
        val after67hour: Float?
    )

    /** 습도 정보 */
    @JsonDeserialize(using = HumidityCollection3DaysDeserializer::class)
    data class HumidityCollection(
        /**
         * - key: 발표시간
         * - value: 습도 정보
         */
        val data: Map<Int, Float>,
        val after4hour: Float,
        val after7hour: Float,
        val after10hour: Float,
        val after13hour: Float,
        val after16hour: Float,
        val after19hour: Float,
        val after22hour: Float,
        val after25hour: Float,
        val after28hour: Float,
        val after31hour: Float,
        val after34hour: Float,
        val after37hour: Float,
        val after40hour: Float,
        val after43hour: Float,
        val after46hour: Float,
        val after49hour: Float,
        val after52hour: Float,
        val after55hour: Float,
        val after58hour: Float,
        val after61hour: Float,
        val after64hour: Float,
        val after67hour: Float?
    )

    /* ============================= 6시간 ============================= */

    /** 누적 강우량 */
    @JsonDeserialize(using = RainCollection3DaysDeserializer::class)
    data class RainCollection(
        val data: Map<Int, Rain>,
        val after6hour: Rain,
        val after12hour: Rain,
        val after18hour: Rain,
        val after24hour: Rain,
        val after30hour: Rain,
        val after36hour: Rain,
        val after42hour: Rain,
        val after48hour: Rain,
        val after54hour: Rain,
        val after60hour: Rain,
        val after66hour: Rain
    )

    /** 6시간 신 적설량 */
    data class SnowCollection(
        val data: Map<Int, Snow>,
        val after6hour: Snow,
        val after12hour: Snow,
        val after18hour: Snow,
        val after24hour: Snow,
        val after30hour: Snow,
        val after36hour: Snow,
        val after42hour: Snow,
        val after48hour: Snow,
        val after54hour: Snow,
        val after60hour: Snow,
        val after66hour: Snow
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
        val min1day: Int?,

        /** 일 최고기온(오늘) */
        val max1day: Int?,

        /** 일 최저기온(내일) */
        val min2day: Int,

        /** 일 최고기온(내일) */
        val max2day: Int,

        /** 일 최저기온(모레) */
        val min3day: Int,

        /** 일 최고기온(모레) */
        val max3day: Int
    )
}
