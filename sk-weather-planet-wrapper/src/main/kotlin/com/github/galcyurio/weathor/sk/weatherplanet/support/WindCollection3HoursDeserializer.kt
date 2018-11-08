package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.WindCollection
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Wind

/**
 * @author galcyurio
 */
class WindCollection3HoursDeserializer
    : StdDeserializer<WindCollection>(WindCollection::class.java) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): WindCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val map = listOf(1, 2, 3, 4)
            .mapNotNull {
                val direction = node["wdir${it}hour"].textValue().toFloatOrNull() ?: return@mapNotNull null
                val speed = node["wspd${it}hour"].floatValue()
                it to Wind(direction, speed)
            }
            .toMap()
        return WindCollection(
            data = map,
            after1hour = map[1],
            after2hour = map[2],
            after3hour = map[3],
            after4hour = map[4]
        )
    }
}
