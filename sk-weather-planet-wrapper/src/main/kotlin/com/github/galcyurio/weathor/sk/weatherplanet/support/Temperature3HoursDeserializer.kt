package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.Temperature

/**
 * @author galcyurio
 */
class Temperature3HoursDeserializer : StdDeserializer<Temperature>(Temperature::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Temperature {
        val node = p.codec.readTree<JsonNode>(p)
        val list = listOf(1, 2, 3, 4).mapNotNull {
            node["temp${it}hour"].textValue().toFloatOrNull()
        }
        return Temperature(list[0], list[1], list[2], list.getOrNull(3))
    }
}