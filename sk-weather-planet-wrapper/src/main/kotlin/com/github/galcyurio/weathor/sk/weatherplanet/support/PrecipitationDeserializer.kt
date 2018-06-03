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
        val typeCode = node.get("type").textValue().toInt()

        return Precipitation(
            sinceOnTime = node.get("sinceOntime").textValue().toFloat(),
            unit = Precipitation.Unit.code(typeCode),
            type = Precipitation.Type.code(typeCode)
        )
    }
}