package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.Temperature

/**
 * @author galcyurio
 */
class Temperature3HoursDeserializer : StdDeserializer<Temperature>(Temperature::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Temperature {
        TODO("not implemented")
    }
}