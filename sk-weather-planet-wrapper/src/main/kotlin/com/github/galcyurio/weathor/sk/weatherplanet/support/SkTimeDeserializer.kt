package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author galcyurio
 */
class SkTimeDeserializer : StdDeserializer<Date>(Date::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Date {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).parse(p.text)
    }
}