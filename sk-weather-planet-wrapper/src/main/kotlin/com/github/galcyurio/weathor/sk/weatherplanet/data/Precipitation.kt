package com.github.galcyurio.weathor.sk.weatherplanet.data

/**
 * 강수정보
 * @author galcyurio
 */
data class Precipitation(
    /** 1시간 누적 강수량 */
    val sinceOnTime: Float,

    /** 단위 */
    val unit: Unit
) {
    enum class Unit { MILLIMETER, CENTIMETER }
}