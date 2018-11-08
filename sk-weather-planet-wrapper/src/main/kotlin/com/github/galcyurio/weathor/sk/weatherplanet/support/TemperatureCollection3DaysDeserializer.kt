package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days.TemperatureCollection

/**
 * @author galcyurio
 */
class TemperatureCollection3DaysDeserializer : StdDeserializer<TemperatureCollection>(TemperatureCollection::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): TemperatureCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val map = (4..67 step 3)
            .map { it to node["temp${it}hour"].textValue() }
            .filter { it.second.isNotEmpty() }
            .map { (hour, temperature) -> hour to temperature.toFloat() }
            .toMap()
        return TemperatureCollection(
            data = map,
            after4hour = map[4],
            after7hour = map[7],
            after10hour = map[10],
            after13hour = map[13],
            after16hour = map[16],
            after19hour = map[19],
            after22hour = map[22],
            after25hour = map[25],
            after28hour = map[28],
            after31hour = map[31],
            after34hour = map[34],
            after37hour = map[37],
            after40hour = map[40],
            after43hour = map[43],
            after46hour = map[46],
            after49hour = map[49],
            after52hour = map[52],
            after55hour = map[55],
            after58hour = map[58],
            after61hour = map[61],
            after64hour = map[64],
            after67hour = map[67]

        )
    }
}