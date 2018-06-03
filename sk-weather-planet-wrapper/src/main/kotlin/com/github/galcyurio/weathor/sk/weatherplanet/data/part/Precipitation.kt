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
    enum class Unit {
        MILLIMETER, CENTIMETER;

        companion object {
            fun code(code: Int) = when (code) {
                in 0..2 -> MILLIMETER
                3 -> CENTIMETER
                else -> throw IllegalArgumentException("잘못된 type 입니다. 0~3 이내의 타입이 아닙니다.")
            }
        }
    }

    enum class Type {
        CLEAR, RAIN, RAIN_SNOW, SNOW;

        companion object {
            fun code(code: Int): Type = when (code) {
                0 -> CLEAR
                1 -> RAIN
                2 -> RAIN_SNOW
                3 -> SNOW
                else -> throw IllegalArgumentException("잘못된 type 입니다. 0~3 이내의 타입이 아닙니다.")
            }
        }
    }
}