package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.galcyurio.weathor.sk.weatherplanet.data.Sky

/**
 * @author galcyurio
 */
class SkyDeserializer : StdDeserializer<Sky>(Sky::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Sky {
        val node = p.codec.readTree<JsonNode>(p)
        return Sky.valueOf(node.get("code").textValue())
    }
}