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
        val actual = mapper.treeToValue<Grid>(elementNode["grid"])
        assertThat(actual).hasNoNullFieldsOrProperties()
    }

    @Test
    fun `WindCollection 부분 역직렬화`() {
        val actual = mapper.treeToValue<WindCollection>(elementNode["wind"])
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    @Test
    fun `PrecipitationCollection 부분 역직렬화`() {
        val actual = mapper.treeToValue<PrecipitationCollection>(elementNode["precipitation"])
        assertThat(actual.data).hasSize(3)
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    @Test
    fun `SkyCollection 부분 역직렬화`() {
        val actual = mapper.treeToValue<SkyCollection>(elementNode["sky"])
        assertThat(actual.data).hasSize(3)
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    @Test
    fun `TemperatureCollection 부분 역직렬화`() {
        val actual = mapper.treeToValue<TemperatureCollection>(elementNode["temperature"])
        assertThat(actual.data).hasSize(3)
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    @Test
    fun `HumidityCollection 부분 역직렬화`() {
        val actual = mapper.treeToValue<HumidityCollection>(elementNode["humidity"])
        assertThat(actual.data).hasSize(3)
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    /* 전체 역직렬화 */

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Common`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).common
        assertThat(actual).hasNoNullFieldsOrProperties()
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Result`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).result
        assertThat(actual).hasNoNullFieldsOrProperties()
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#Grid`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].grid
        assertThat(actual).hasNoNullFieldsOrProperties()
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#WindCollection`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].wind
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#PrecipitationCollection`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].precipitation
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#SkyCollection`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].sky
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#Temperature`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].temperature
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#Humidity`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].humidity
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element#LightningCollection`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0].lightning
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after4hour")
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화 후 Element`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode).elements[0]
        assertThat(actual).hasNoNullFieldsOrProperties()
    }

}