package com.github.galcyurio.weathor.sk.weatherplanet

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.galcyurio.weathor.sk.weatherplanet.data.CurrentWeatherMinutely
import org.junit.Before
import org.junit.Test

/**
 * @author galcyurio
 */
class DeserializerTest {
    private lateinit var mapper: ObjectMapper

    @Before
    fun setUp() {
        mapper = ObjectMapper().registerKotlinModule()
    }

    @Test
    fun `CurrentWeatherMinutely json 파일 역직렬화 테스트`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/current-weather-minutely.json")
        val currentWeatherMinutely = mapper.readValue(inputStream, CurrentWeatherMinutely::class.java)

        println(currentWeatherMinutely)
    }
}