package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Precipitation

/**
 * - if type=0/1/2 --> 강우량(mm)
 * - if type=3 --> 적설량(cm)
 * @author galcyurio
 */
class PrecipitationDeserializer : StdDeserializer<Precipitation>(Precipitation::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Precipitation {
        val node = p.codec.readTree<JsonNode>(p)
        val unit: Precipitation.Unit
        val type: Precipitation.Type

        when (node.get("type").textValue().toInt()) {
            0 -> {
                unit = Precipitation.Unit.MILLIMETER
                type = Precipitation.Type.CLEAR
            }
            1 -> {
                unit = Precipitation.Unit.MILLIMETER
                type = Precipitation.Type.RAIN
            }
            2 -> {
                unit = Precipitation.Unit.MILLIMETER
                type = Precipitation.Type.RAIN_SNOW
            }
            3 -> {
                unit = Precipitation.Unit.CENTIMETER
                type = Precipitation.Type.SNOW
            }
            else -> throw IllegalArgumentException("잘못된 type 입니다. 0~3 이내의 타입이 아닙니다.")
        }

        return Precipitation(
            sinceOnTime = node.get("sinceOntime").textValue().toFloat(),
            unit = unit,
            type = type
        )
    }
}