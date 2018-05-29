package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.CurrentWeatherHourly
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Common
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Result

/**
 * @author galcyurio
 */
class CurrentWeatherHourlyDeserializer : StdDeserializer<CurrentWeatherHourly>(CurrentWeatherHourly::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): CurrentWeatherHourly {
        val codec = p.codec
        val node = codec.readTree<JsonNode>(p)

        return CurrentWeatherHourly(
            elements = node.get("weather").get("hourly").asSequence()
                .map { codec.treeToValue(it, CurrentWeatherHourly.Element::class.java) }
                .toList(),
            common = codec.treeToValue(node.get("common"), Common::class.java),
            result = codec.treeToValue(node.get("result"), Result::class.java)
        )
    }
}