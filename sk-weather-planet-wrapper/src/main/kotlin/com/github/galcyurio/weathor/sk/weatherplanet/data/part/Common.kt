package com.github.galcyurio.weathor.sk.weatherplanet.data.part

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.galcyurio.weathor.sk.weatherplanet.support.CommonDeserializer

/**
 * @author galcyurio
 */
@JsonDeserialize(using = CommonDeserializer::class)
data class Common(
    /** 특보 존재유무(한반도 전역 기준) */
    val alertYn: Boolean,

    /** 태풍 존재유무(경계구역 기준) */
    val stormYn: Boolean
)
