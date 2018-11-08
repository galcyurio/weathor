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
        val list = map.values.toList()

        return WindCollection(
            data = map,
            after1hour = list.getOrNull(0),
            after2hour = list.getOrNull(1),
            after3hour = list.getOrNull(2),
            after4hour = list.getOrNull(3)
        )
    }
}
