package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.util.*

/**
 * @author galcyurio
 */
object Injector {
    fun provideObjectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerKotlinModule()
            .registerModule(SimpleModule()
                .addDeserializer(Date::class.java, SkTimeDeserializer()))
    }
}