package com.github.galcyurio.weathor.sk.weatherplanet

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.galcyurio.weathor.sk.weatherplanet.data.CurrentWeatherHourly
import com.github.galcyurio.weathor.sk.weatherplanet.data.CurrentWeatherMinutely
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours
import com.github.galcyurio.weathor.sk.weatherplanet.support.Injector
import org.junit.Test

/**
 * @author galcyurio
 */
class DeserializerTest {
    private val mapper: ObjectMapper = Injector.provideObjectMapper()

    @Test
    fun `CurrentWeatherMinutely json 파일 역직렬화`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/current-weather-minutely.json")
        mapper.readValue(inputStream, CurrentWeatherMinutely::class.java)
    }

    @Test
    fun `CurrentWeatherHourly json 파일 역직렬화`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/current-weather-hourly.json")
        mapper.readValue(inputStream, CurrentWeatherHourly::class.java)
    }

    @Test
    fun `ForecastWeather3Hours json 파일 역직렬화`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/forecast-3hours.json")
        mapper.readValue(inputStream, ForecastWeather3Hours::class.java)
    }
}