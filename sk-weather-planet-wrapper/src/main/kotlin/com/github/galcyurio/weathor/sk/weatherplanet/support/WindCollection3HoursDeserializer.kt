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
        val list = listOf(1, 2, 3, 4).mapNotNull {
            val direction = node["wdir${it}hour"].textValue().toFloatOrNull()
            val speed = node["wspd${it}hour"].textValue().toFloatOrNull()
            if (direction != null && speed != null) Wind(direction, speed) else null
        }
        return WindCollection(list[0], list[1], list[2], list.getOrNull(3))
    }
}
