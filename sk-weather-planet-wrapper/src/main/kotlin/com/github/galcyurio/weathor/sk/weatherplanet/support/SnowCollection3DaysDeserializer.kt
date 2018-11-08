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
        return SnowCollection(
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