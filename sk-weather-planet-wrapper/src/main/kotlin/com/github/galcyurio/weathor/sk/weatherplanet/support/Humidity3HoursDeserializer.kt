package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.Humidity

/**
 * @author galcyurio
 */
class Humidity3HoursDeserializer : StdDeserializer<Humidity>(Humidity::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Humidity {
        TODO("not implemented")
    }
}