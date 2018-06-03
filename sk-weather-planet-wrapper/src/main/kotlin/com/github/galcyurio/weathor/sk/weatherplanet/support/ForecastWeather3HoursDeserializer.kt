package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours

/**
 * @author galcyurio
 */
class ForecastWeather3HoursDeserializer
    : StdDeserializer<ForecastWeather3Hours>(ForecastWeather3Hours::class.java) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ForecastWeather3Hours {
        TODO("not implemented")
    }
}