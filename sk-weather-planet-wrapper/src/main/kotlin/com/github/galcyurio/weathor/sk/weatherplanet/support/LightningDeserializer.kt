package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer

/**
 * @author galcyurio
 */
class LightningDeserializer : StdDeserializer<Boolean>(Boolean::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Boolean {
        return when (p.text) {
            "0" -> false
            "1" -> true
            else -> throw IllegalArgumentException("0, 1 이외의 문자열입니다.")
        }
    }
}