package com.github.galcyurio.weathor.sk.weatherplanet.data.part

/**
 * @author galcyurio
 */
enum class Lightning {
    /** 현상 없음 */
    CLEAR,

    /** 낮음 (30~50%) */
    LOW,

    /** 보통 (50~70%) */
    MIDDLE,

    /** 높음 (70% 이상) */
    HIGH;

    companion object {
        fun code(code: Int) = when(code) {
            0 -> CLEAR
            1 -> LOW
            2 -> MIDDLE
            3 -> HIGH
            else -> throw IllegalArgumentException("0~3 이외의 code 입니다.")
        }
    }
}
