package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days.SnowCollection

/**
 * @author galcyurio
 */
class SnowCollection3DaysDeserializer : StdDeserializer<SnowCollection>(SnowCollection::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SnowCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val map = (6..66 step 6)
            .map {
                val raw = node["snow${it}hour"].textValue()
                val snow = when (raw) {
                    "없음" -> ForecastWeather3Days.Snow.NONE
                    "1cm미만" -> ForecastWeather3Days.Snow.UNDER_1_CM
                    "1~4cm" -> ForecastWeather3Days.Snow.BETWEEN_1_4_CM
                    "5~9cm" -> ForecastWeather3Days.Snow.BETWEEN_5_9_CM
                    "10~19cm" -> ForecastWeather3Days.Snow.BETWEEN_10_19_CM
                    "20cm이상" -> ForecastWeather3Days.Snow.MORE_THAN_20_CM
                    else -> throw IllegalArgumentException("wrong snow string")
                }
                it to snow
            }
            .toMap()
        val list = map.values.toList()
        return SnowCollection(
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