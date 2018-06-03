package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.SkyCollection
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Sky

/**
 * @author galcyurio
 */
class SkyCollection3HoursDeserializer
    : StdDeserializer<SkyCollection>(SkyCollection::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SkyCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val list = listOf(1, 2, 3, 4).mapNotNull {
            val code = node["code${it}hour"].textValue()
            if (code.isEmpty()) return@mapNotNull null
            Sky.valueOf(code)
        }
        return SkyCollection(list[0], list[1], list[2], list.getOrNull(3))
    }
}