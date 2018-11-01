package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.PrecipitationCollection
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Precipitation

/**
 * @author galcyurio
 */
class PrecipitationCollection3HoursDeserializer
    : StdDeserializer<PrecipitationCollection>(PrecipitationCollection::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): PrecipitationCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val map = listOf(1, 2, 3, 4)
            .mapNotNull {
                val sinceOnTime = node["sinceOntime${it}hour"].textValue().toFloatOrNull() ?: return@mapNotNull null
                val typeCode = node["type${it}hour"].intValue()
                val type = Precipitation.Type.code(typeCode)
                val unit = Precipitation.Unit.code(typeCode)

                it to Precipitation(sinceOnTime, unit, type)
            }
            .toMap()
        val precipitations = map.values.toList()

        return PrecipitationCollection(
            data = map,
            after1hour = precipitations[0],
            after2hour = precipitations[1],
            after3hour = precipitations[2],
            after4hour = precipitations.getOrNull(3)
        )
    }
}