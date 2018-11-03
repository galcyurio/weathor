package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days.WindCollection
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Wind

/**
 * @author galcyurio
 */
class WindCollection3DaysDeserializer : StdDeserializer<WindCollection>(WindCollection::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): WindCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val map = (4..67 step 3)
            .mapNotNull {
                val direction = node["wdir${it}hour"].textValue().toFloatOrNull() ?: return@mapNotNull null
                val speed = node["wspd${it}hour"].textValue().toFloatOrNull() ?: return@mapNotNull null
                it to Wind(direction, speed)
            }
            .toMap()
        val winds = map.values.toList()
        return WindCollection(
            data = map,
            after4hour = winds[0],
            after7hour = winds[1],
            after10hour = winds[2],
            after13hour = winds[3],
            after16hour = winds[4],
            after19hour = winds[5],
            after22hour = winds[6],
            after25hour = winds[7],
            after28hour = winds[8],
            after31hour = winds[9],
            after34hour = winds[10],
            after37hour = winds[11],
            after40hour = winds[12],
            after43hour = winds[13],
            after46hour = winds[14],
            after49hour = winds[15],
            after52hour = winds[16],
            after55hour = winds[17],
            after58hour = winds[18],
            after61hour = winds[19],
            after64hour = winds[20],
            after67hour = winds.getOrNull(21)
        )
    }
}