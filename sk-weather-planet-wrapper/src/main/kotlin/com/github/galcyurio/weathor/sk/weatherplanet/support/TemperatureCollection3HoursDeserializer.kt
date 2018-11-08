package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.TemperatureCollection

/**
 * @author galcyurio
 */
class TemperatureCollection3HoursDeserializer
    : StdDeserializer<TemperatureCollection>(TemperatureCollection::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): TemperatureCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val map = listOf(1, 2, 3, 4)
            .map { it to node["temp${it}hour"].textValue() }
            .filter { (_, rawTemperature) -> rawTemperature.isNotEmpty() }
            .map { (hour, rawTemperature) -> hour to rawTemperature.toFloat() }
            .toMap()
        return TemperatureCollection(
            data = map,
            after1hour = map[1],
            after2hour = map[2],
            after3hour = map[3],
            after4hour = map[4]
        )
    }
}