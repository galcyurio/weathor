package com.github.galcyurio.weathor.sk.weatherplanet

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.treeToValue
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.*
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Grid
import com.github.galcyurio.weathor.sk.weatherplanet.support.Injector
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * @author galcyurio
 */
class ForecastWeather3HoursDeserializerTest {

    private val mapper = Injector.provideObjectMapper()
    private val rootNode: JsonNode
    private val elementNode: JsonNode

    init {
        val stream = javaClass.classLoader.getResourceAsStream("mock/forecast-3hours.json")
        rootNode = mapper.readTree(stream)
        elementNode = rootNode["weather"]["forecast3hours"][0]
    }

    @Test
    fun `Grid 부분 역직렬화`() {
        mapper.treeToValue<Grid>(elementNode["grid"])
    }

    @Test
    fun `WindCollection 부분 역직렬화`() {
        mapper.treeToValue<WindCollection>(elementNode["wind"])
    }

    @Test
    fun `PrecipitationCollection 부분 역직렬화`() {
        mapper.treeToValue<PrecipitationCollection>(elementNode["precipitation"])
    }

    @Test
    fun `SkyCollection 부분 역직렬화`() {
        mapper.treeToValue<SkyCollection>(elementNode["sky"])
    }

    @Test
    fun `TemperatureCollection 부분 역직렬화`() {
        mapper.treeToValue<TemperatureCollection>(elementNode["temperature"])
    }

    @Test
    fun `HumidityCollection 부분 역직렬화`() {
        mapper.treeToValue<HumidityCollection>(elementNode["humidity"])
    }

    /* 전체 역직렬화 */

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Common`() {
        mapper.treeToValue<ForecastWeather3Hours>(rootNode).common
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Result`() {
        mapper.treeToValue<ForecastWeather3Hours>(rootNode).result
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#Grid`() {
        mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].grid
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#WindCollection`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].wind
        assertThat(actual.data).hasSize(3)
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#PrecipitationCollection`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].precipitation
        assertThat(actual.data).hasSize(3)
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#SkyCollection`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].sky
        assertThat(actual.data).hasSize(3)
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#Temperature`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].temperature
        assertThat(actual.data).hasSize(3)
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#Humidity`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].humidity
        assertThat(actual.data).hasSize(3)
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#LightningCollection`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].lightning
        assertThat(actual.data).hasSize(3)
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element`() {
        mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0]
    }

}