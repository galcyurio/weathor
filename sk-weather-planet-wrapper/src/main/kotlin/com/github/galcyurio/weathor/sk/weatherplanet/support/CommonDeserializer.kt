package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.Common

/**
 * @author galcyurio
 */
class CommonDeserializer : StdDeserializer<Common>(Common::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Common {
        val node = p.codec.readTree<JsonNode>(p)
        return Common(
            alertYn = node.get("alertYn").textValue().ynToBoolean(),
            stormYn = node.get("stormYn").textValue().ynToBoolean()
        )
    }

    private fun String.ynToBoolean(): Boolean = when (this) {
        "Y" -> true
        "N" -> false
        else -> throw IllegalArgumentException("Y, N 이외의 값입니다.")
    }
}