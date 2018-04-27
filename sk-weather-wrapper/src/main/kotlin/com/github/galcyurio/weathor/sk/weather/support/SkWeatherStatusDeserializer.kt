package com.github.galcyurio.weathor.sk.weather.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weather.data.SkWeatherStatus

/**
 * @author galcyurio
 */
class SkWeatherStatusDeserializer : StdDeserializer<SkWeatherStatus>(SkWeatherStatus::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SkWeatherStatus {
        val jsonNode = p.codec.readTree<JsonNode>(p)

        val weatherStatusCode = jsonNode.get("weatherStatusCode").intValue()
        val weatherStatusDescription = jsonNode.get("weatherStatusDescription").textValue()
        val weatherModifyCode = jsonNode.get("weatherModifyCode").intValue()
        val weatherModifyDescription = jsonNode.get("weatherModifyDescription").textValue()

        return SkWeatherStatus(
            status = SkWeatherStatus.Status.values().asSequence()
                .find { it.code == weatherStatusCode }!!,
            statusDescription = weatherStatusDescription,
            modify = SkWeatherStatus.Modify.values().asSequence()
                .find { it.code == weatherModifyCode }!!,
            modifyDescription = weatherModifyDescription
        )
    }
}