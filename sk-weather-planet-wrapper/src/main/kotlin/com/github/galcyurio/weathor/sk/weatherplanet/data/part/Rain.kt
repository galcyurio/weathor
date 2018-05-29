package com.github.galcyurio.weathor.sk.weatherplanet.data.part

/**
 * 강우 정보
 * @author galcyurio
 */
data class Rain(
    /** 1시간 누적 강우량 */
    val sinceOntime: Float,

    /** 일 누적 강우량 */
    val sinceMidnight: Float,

    /** 10분 이동누적 강우량 */
    val last10min: Float,

    /** 15분 이동누적 강우량 */
    val last15min: Float,

    /** 30분 이동누적 강우량 */
    val last30min: Float,

    /** 1시간 이동누적 강우량 */
    val last1hour: Float,

    /** 6시간 이동누적 강우량 */
    val last6hour: Float,

    /** 12시간 이동누적 강우량 */
    val last12hour: Float,

    /** 24시간 이동누적 강우량 */
    val last24hour: Float
)