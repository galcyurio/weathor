package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.Common
import com.github.galcyurio.weathor.sk.weatherplanet.data.CurrentWeatherMinutely
import com.github.galcyurio.weathor.sk.weatherplanet.data.Result

/**
 * @author galcyurio
 */
class CurrentWeatherMinutelyDeserializer : StdDeserializer<CurrentWeatherMinutely>(CurrentWeatherMinutely::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): CurrentWeatherMinutely {
        val codec = p.codec
        val node = codec.readTree<JsonNode>(p)
        val elementsNode = node.get("weather").get("minutely")

        val elements = elementsNode.asSequence()
            .map { codec.treeToValue(it, CurrentWeatherMinutely.Element::class.java) }
            .toList()
        val common = codec.treeToValue(node.get("common"), Common::class.java)
        val result = codec.treeToValue(node.get("result"), Result::class.java)

        return CurrentWeatherMinutely(elements, common, result)
    }

}