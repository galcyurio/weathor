package com.github.galcyurio.weathor.sk.weatherplanet.data.part

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.galcyurio.weathor.sk.weatherplanet.support.SkyDeserializer

/**
 * 하늘상태 정보
 * @author galcyurio
 */
@JsonDeserialize(using = SkyDeserializer::class)
enum class Sky(val korean: String) {
    /** 맑음 */
    SKY_A01("맑음"),

    /** 구름조금 */
    SKY_A02("구름조금"),

    /** 구름많음 */
    SKY_A03("구름많음"),

    /** 구름많고 비 */
    SKY_A04("구름많고 비"),

    /** 구름많고 눈 */
    SKY_A05("구름많고 눈"),

    /** 구름많고 비 또는 눈 */
    SKY_A06("구름많고 비 또는 눈"),

    /** 흐림 */
    SKY_A07("흐림"),

    /** 흐리고 비 */
    SKY_A08("흐리고 비"),

    /** 흐리고 눈 */
    SKY_A09("흐리고 눈"),

    /** 흐리고 비 또는 눈 */
    SKY_A10("흐리고 비 또는 눈"),

    /** 흐리고 낙 */
    SKY_A11("흐리고 낙"),

    /** 뇌우/비 */
    SKY_A12("뇌우/비"),

    /** 뇌우/눈 */
    SKY_A13("뇌우/눈"),

    /** 뇌우/비 또는 눈 */
    SKY_A14("뇌우/비 또는 눈")
}
