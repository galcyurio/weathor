package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.HumidityCollection

/**
 * @author galcyurio
 */
class HumidityCollection3HoursDeserializer
    : StdDeserializer<HumidityCollection>(HumidityCollection::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): HumidityCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val map = listOf(1, 2, 3, 4)
            .map { it to node["rh${it}hour"].textValue() }
            .filter { (_, rawHumidity) -> rawHumidity.isNotEmpty() }
            .map { (hour, rawHumidity) -> hour to rawHumidity.toFloat() }
            .toMap()

        val list = map.values.toList()

        return HumidityCollection(
            data = map,
            after1hour = list[0],
            after2hour = list[1],
            after3hour = list[2],
            after4hour = list.getOrNull(3)
        )
    }
}