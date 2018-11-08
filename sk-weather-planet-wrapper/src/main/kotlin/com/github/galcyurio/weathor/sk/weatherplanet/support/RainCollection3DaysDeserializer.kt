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
            .mapNotNull {
                val raw = node["rain${it}hour"].textValue()
                if (raw.isEmpty()) return@mapNotNull null

                val rain = when (raw) {
                    "없음" -> NONE
                    "1mm미만" -> UNDER_1_MM
                    "1~4mm" -> BETWEEN_1_4_MM
                    "5~9mm" -> BETWEEN_5_9_MM
                    "10~19mm" -> BETWEEN_10_19_MM
                    "20~39mm" -> BETWEEN_20_39_MM
                    "40~69mm" -> BETWEEN_40_69_MM
                    "70mm이상" -> MORE_THAN_70_MM
                    else -> throw IllegalArgumentException("wrong rain string : $raw")
                }
                it to rain
            }
            .toMap()
        return RainCollection(
            data = map,
            after6hour = map[6],
            after12hour = map[12],
            after18hour = map[18],
            after24hour = map[24],
            after30hour = map[30],
            after36hour = map[36],
            after42hour = map[42],
            after48hour = map[48],
            after54hour = map[54],
            after60hour = map[60],
            after66hour = map[66]
        )
    }
}