package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.SkyCollection
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Sky

/**
 * @author galcyurio
 */
class SkyCollection3HoursDeserializer
    : StdDeserializer<SkyCollection>(SkyCollection::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SkyCollection {
        val node = p.codec.readTree<JsonNode>(p)
        val map = listOf(1, 2, 3, 4)
            .map { it to node["code${it}hour"].textValue() }
            .filterNot { (_, code) -> code.isNullOrEmpty() }
            .map { (hour, code) -> hour to Sky.valueOf(code) }
            .toMap()
        return SkyCollection(
            data = map,
            after1hour = map[1],
            after2hour = map[2],
            after3hour = map[3],
            after4hour = map[4]
        )
    }
}