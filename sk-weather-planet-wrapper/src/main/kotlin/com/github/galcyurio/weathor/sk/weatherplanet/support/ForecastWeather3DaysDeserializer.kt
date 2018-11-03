package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days

/**
 * @author galcyurio
 */
class ForecastWeather3DaysDeserializer : StdDeserializer<ForecastWeather3Days>(ForecastWeather3Days::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ForecastWeather3Days {
        val codec = p.codec
        val node = codec.readTree<JsonNode>(p)
        return ForecastWeather3Days(
            elements = codec.treeToValue(node["weather"]["forecast3days"]),
            common = codec.treeToValue(node["common"]),
            result = codec.treeToValue(node["result"])
        )
    }
}