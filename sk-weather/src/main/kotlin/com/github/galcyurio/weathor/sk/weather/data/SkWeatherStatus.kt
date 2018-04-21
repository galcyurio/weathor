package com.github.galcyurio.weathor.sk.weather.data

/**
 * 특정 위치, 시간 기준 날씨 Context
 * @author galcyurio
 */
data class SkWeatherStatus(
    val status: Status,
    val statusDescription: String,
    val modify: Modify,
    val modifyDescription: String
) {
    companion object {
        fun fromRaw(raw: RawSkWeatherStatus): SkWeatherStatus = SkWeatherStatus(
            status = Status.values().asSequence()
                .find { it.code == raw.weatherStatusCode }!!,
            statusDescription = raw.weatherStatusDescription,
            modify = Modify.values().asSequence()
                .find { it.code == raw.weatherModifyCode }!!,
            modifyDescription = raw.weatherModifyDescription
        )
    }

    /**
     * 상태 (명사)
     */
    enum class Status(val code: Int) {
        /**
         * 알수 없음
         */
        UNKNOWN(0),

        /**
         * 맑음
         */
        FINE(1),

        /**
         * 흐림
         */
        CLOUDY(2),

        /**
         * 안개
         */
        FOG(3),

        /**
         * 구름
         */
        CLOUDS(4),

        /**
         * 비
         */
        RAIN(5),

        /**
         * 눈
         */
        SNOW(6),

        /**
         * 비/눈
         */
        RAIN_SNOW(7),

        /**
         * 폭우
         */
        HEAVY_RAIN(8),

        /**
         * 폭설
         */
        SNOWFALL(9),

        /**
         * 폭우/폭설
         */
        HEAVY_RAIN_SNOWFALL(10)
    }

    /**
     * 수식어
     */
    enum class Modify(val code: Int) {

        /**
         * 알수 없음
         */
        UNKNOWN(0),

        /**
         * 강추위(한파)
         */
        VERY_COLD(1),

        /**
         * 춥
         */
        COLD(2),

        /**
         * 쌀쌀하다
         */
        CHILLY(3),

        /**
         * 포근하다
         */
        COMFORTABLE(4),

        /**
         * 따듯하다
         */
        WARM(5),

        /**
         * 선선하다
         */
        COOL(6),

        /**
         * 덥다
         */
        HOT(7),

        /**
         * 무더위(폭염)
         */
        HEAT(8)
    }
}