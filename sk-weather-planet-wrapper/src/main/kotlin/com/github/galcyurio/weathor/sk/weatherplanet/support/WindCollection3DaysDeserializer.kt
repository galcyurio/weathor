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
            after4hour = winds.getOrNull(0),
            after7hour = winds.getOrNull(1),
            after10hour = winds.getOrNull(2),
            after13hour = winds.getOrNull(3),
            after16hour = winds.getOrNull(4),
            after19hour = winds.getOrNull(5),
            after22hour = winds.getOrNull(6),
            after25hour = winds.getOrNull(7),
            after28hour = winds.getOrNull(8),
            after31hour = winds.getOrNull(9),
            after34hour = winds.getOrNull(10),
            after37hour = winds.getOrNull(11),
            after40hour = winds.getOrNull(12),
            after43hour = winds.getOrNull(13),
            after46hour = winds.getOrNull(14),
            after49hour = winds.getOrNull(15),
            after52hour = winds.getOrNull(16),
            after55hour = winds.getOrNull(17),
            after58hour = winds.getOrNull(18),
            after61hour = winds.getOrNull(19),
            after64hour = winds.getOrNull(20),
            after67hour = winds.getOrNull(21)
        )
    }
}