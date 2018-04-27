package com.github.galcyurio.weathor.sk.weather.support

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.galcyurio.weathor.sk.weather.data.SkWeatherStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.File

/**
 * @author galcyurio
 */
class SkWeatherStatusDeserializerTest {

    @Test
    fun `SkWeatherStatusDeserializer를 통해 역직렬화가 제대로 이루어지는지 확인한다`() {
        val objectMapper = ObjectMapper()
            .registerKotlinModule()
            .registerModule(SimpleModule()
                .addDeserializer(SkWeatherStatus::class.java, SkWeatherStatusDeserializer()))

        val json = File(javaClass.classLoader.getResource("mock/WeatherStatus.json").toURI()).readText()

        val skWeatherStatus = objectMapper.readValue(json, SkWeatherStatus::class.java)
        println(skWeatherStatus)
        assertThat(skWeatherStatus).isNotNull()
    }
}