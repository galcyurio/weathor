package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days.Rain.*
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days.RainCollection

/**
 * @author galcyurio
 */
class RainCollection3DaysDeserializer : StdDeserializer<RainCollection>(RainCollection::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): RainCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val map = (6..66 step 6)
            .map {
                val raw = node["rain${it}hour"].textValue()
                val rain = when (raw) {
                    "없음" -> NONE
                    "1mm미만" -> UNDER_1_MM
                    "1~4mm" -> BETWEEN_1_4_MM
                    "5~9mm" -> BETWEEN_5_9_MM
                    "10~19mm" -> BETWEEN_10_19_MM
                    "20~39mm" -> BETWEEN_20_39_MM
                    "40~69mm" -> BETWEEN_40_69_MM
                    "70mm이상" -> MORE_THAN_70_MM
                    else -> throw IllegalArgumentException("wrong rain string")
                }
                it to rain
            }
            .toMap()
        val list = map.values.toList()
        return RainCollection(
            data = map,
            after6hour = list[0],
            after12hour = list[1],
            after18hour = list[2],
            after24hour = list[3],
            after30hour = list[4],
            after36hour = list[5],
            after42hour = list[6],
            after48hour = list[7],
            after54hour = list[8],
            after60hour = list[9],
            after66hour = list[10]
        )
    }
}