package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days.HumidityCollection

/**
 * @author galcyurio
 */
class HumidityCollection3DaysDeserializer : StdDeserializer<HumidityCollection>(HumidityCollection::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): HumidityCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val map = (4..67 step 3)
            .map { it to node["rh${it}hour"].textValue() }
            .filter { it.second.isNotEmpty() }
            .map { (hour, humidity) -> hour to humidity.toFloat() }
            .toMap()
        val list = map.values.toList()
        return HumidityCollection(
            data = map,
            after4hour = list[0],
            after7hour = list[1],
            after10hour = list[2],
            after13hour = list[3],
            after16hour = list[4],
            after19hour = list[5],
            after22hour = list[6],
            after25hour = list[7],
            after28hour = list[8],
            after31hour = list[9],
            after34hour = list[10],
            after37hour = list[11],
            after40hour = list[12],
            after43hour = list[13],
            after46hour = list[14],
            after49hour = list[15],
            after52hour = list[16],
            after55hour = list[17],
            after58hour = list[18],
            after61hour = list[19],
            after64hour = list[20],
            after67hour = list.getOrNull(21)
        )
    }
}