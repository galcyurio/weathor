package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.*
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Common
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Grid
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Lightning
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Result
import java.util.*

/**
 * @author galcyurio
 */
class ForecastWeather3HoursDeserializer
    : StdDeserializer<ForecastWeather3Hours>(ForecastWeather3Hours::class.java) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ForecastWeather3Hours {
        val codec = p.codec as ObjectMapper
        val node = codec.readTree<JsonNode>(p)
        val elementsNode = node["weather"]["forecast3hours"]

        val elements = elementsNode.map { elementNode ->
            Element(
                grid = codec.treeToValue(elementNode["grid"], Grid::class.java),
                wind = codec.treeToValue(elementNode["wind"], WindCollection::class.java),
                precipitation = codec.treeToValue(elementNode["precipitation"], PrecipitationCollection::class.java),
                sky = codec.treeToValue(elementNode["sky"], SkyCollection::class.java),
                temperature = codec.treeToValue(elementNode["temperature"], TemperatureCollection::class.java),
                humidity = codec.treeToValue(elementNode["humidity"], HumidityCollection::class.java),
                lightning = nodeToLightningCollection(elementNode),
                releaseTime = codec.treeToValue(elementNode["timeRelease"], Date::class.java)
            )
        }

        return ForecastWeather3Hours(
            elements = elements,
            common = codec.treeToValue(node["common"], Common::class.java),
            result = codec.treeToValue(node["result"], Result::class.java)
        )
    }

    internal fun nodeToLightningCollection(node: JsonNode): LightningCollection {
        val lightningList = listOf(1, 2, 3, 4)
            .mapNotNull {
                val code = node.get("lightning${it}hour").textValue().toIntOrNull() ?: return@mapNotNull null
                Lightning.code(code)
            }

        return LightningCollection(
            lightningList[0],
            lightningList[1],
            lightningList[2],
            lightningList.getOrNull(3)
        )
    }
}