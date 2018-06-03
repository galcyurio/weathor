package com.github.galcyurio.weathor.sk.weatherplanet.data.part

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.galcyurio.weathor.sk.weatherplanet.support.PrecipitationDeserializer

/**
 * 강수정보
 * @author galcyurio
 */
@JsonDeserialize(using = PrecipitationDeserializer::class)
data class Precipitation(
    /** 1시간 누적 강수량 */
    val sinceOnTime: Float,

    /** 단위 */
    val unit: Unit,

    /** 강수 형태 */
    val type: Type
) {
    enum class Unit { MILLIMETER, CENTIMETER }

    enum class Type { CLEAR, RAIN, RAIN_SNOW, SNOW }
}