package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.treeToValue
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days.Element

/**
 * @author galcyurio
 */
class ForecastWeather3DaysDeserializer : StdDeserializer<ForecastWeather3Days>(ForecastWeather3Days::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ForecastWeather3Days {
        val codec = p.codec as ObjectMapper
        val node = codec.readTree<JsonNode>(p)

        return ForecastWeather3Days(
            elements = codec.readValue(
                codec.treeAsTokens(node["weather"]["forecast3days"]),
                codec.typeFactory.constructCollectionType(List::class.java, Element::class.java)),
            common = codec.treeToValue(node["common"]),
            result = codec.treeToValue(node["result"])
        )
    }
}