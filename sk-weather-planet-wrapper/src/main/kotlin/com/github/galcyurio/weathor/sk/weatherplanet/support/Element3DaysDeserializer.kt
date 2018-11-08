package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.treeToValue
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days.Element

/**
 * @author galcyurio
 */
class Element3DaysDeserializer : StdDeserializer<Element>(Element::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Element {
        val codec = p.codec as ObjectMapper
        val node = codec.readTree<JsonNode>(p)
        val fcst3hourNode = node["fcst3hour"]
        val fcst6hourNode = node["fcst6hour"]
        val fcstdailyNode = node["fcstdaily"]

        return Element(
            grid = codec.treeToValue(node["grid"]),
            releaseTime = codec.treeToValue(node["timeRelease"]),
            wind = codec.treeToValue(fcst3hourNode["wind"]),
            precipitation = codec.treeToValue(fcst3hourNode["precipitation"]),
            sky = codec.treeToValue(fcst3hourNode["sky"]),
            temperature = codec.treeToValue(fcst3hourNode["temperature"]),
            humidity = codec.treeToValue(fcst3hourNode["humidity"]),
            rain = codec.treeToValue(fcst6hourNode),
            snow = codec.treeToValue(fcst6hourNode),
            temperatureMinMax = codec.treeToValue(fcstdailyNode["temperature"])
        )
    }
}
