package com.github.galcyurio.weathor.sk.weatherplanet.data

/**
 * 관측소 정보
 * @author galcyurio
 */
data class Station(
    /** 관측소 위도 */
    val latitude: Double,

    /** 관측소 경도 */
    val longitude: Double,

    /** 관측소 이름 */
    val name: String,

    /** 관측소 지점번호 */
    val id: String,

    /** 관측소 유형 */
    val type: Type
) {
    enum class Type {
        /** 기상청 관측소 */
        KMA,

        /** SKTX 관측소 */
        BTN
    }
}