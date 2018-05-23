package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.Precipitation

/**
 * - if type=0/1/2 --> 강우량(mm)
 * - if type=3 --> 적설량(cm)
 * @author galcyurio
 */
class PrecipitationDeserializer : StdDeserializer<Precipitation>(Precipitation::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Precipitation {
        val node = p.codec.readTree<JsonNode>(p)
        return Precipitation(
            sinceOnTime = node.get("sinceOntime").textValue().toFloat(),
            unit = when (node.get("type").textValue().toInt()) {
                in 0..2 -> Precipitation.Unit.MILLIMETER
                3 -> Precipitation.Unit.CENTIMETER
                else -> throw IllegalArgumentException("잘못된 type 입니다. 0~3 이내의 타입이 아닙니다.")
            }
        )
    }
}