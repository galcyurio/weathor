package com.github.galcyurio.weathor.sk.weatherplanet.data

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.galcyurio.weathor.sk.weatherplanet.support.LightningDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.support.SkTimeDeserializer
import java.util.*

/**
 * 1분 단위 현재날씨 정보
 * @author galcyurio
 */
data class CurrentWeatherMinutely(
    /** 강수 정보 */
    val precipitation: Precipitation,

    /** 하늘 상태 정보 */
    val sky: Sky,

    /** 강우 정보 */
    val rain: Rain,

    /** 기온 정보 */
    val temperature: Temperature,

    /** 상대 습도 (%) */
    val humidity: Float,

    /** 기압 정보 */
    val pressure: Pressure,

    /** 낙뢰 유무 (관측소 5km 반경 내) */
    @JsonDeserialize(using = LightningDeserializer::class)
    val lightning: Boolean,

    /** 관측 시간 */
    @JsonDeserialize(using = SkTimeDeserializer::class)
    val observationTime: Date,

    val common: Common,

    val result: Result
)
