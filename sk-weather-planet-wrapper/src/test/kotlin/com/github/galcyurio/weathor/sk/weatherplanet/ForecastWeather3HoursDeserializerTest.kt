package com.github.galcyurio.weathor.sk.weatherplanet

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.module.kotlin.treeToValue
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours.*
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Common
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Grid
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * @author galcyurio
 */
class ForecastWeather3HoursDeserializerTest {

    private val mapper = ObjectMapper().registerKotlinModule()
    private val rootNode: JsonNode
    private val elementNode: JsonNode

    init {
        val stream = javaClass.classLoader.getResourceAsStream("mock/forecast-3hours.json")
        rootNode = mapper.readTree(stream)
        elementNode = rootNode["weather"]["forecast3hours"][0]
    }

    @Test
    fun `ForecastWeather3Hours 전체 역직렬화`() {
        val actual = mapper.treeToValue<ForecastWeather3Hours>(rootNode)
        println(actual)
    }

    @Test
    fun `Common 부분 역직렬화`() {
        val actual = mapper.treeToValue<Common>(rootNode["common"])
        assertThat(actual).hasNoNullFieldsOrProperties()
    }

    @Test
    fun `Result 부분 역직렬화`() {
        val actual = mapper.treeToValue<Result>(rootNode["result"])
        assertThat(actual).hasNoNullFieldsOrProperties()
    }

    @Test
    fun `Grid 부분 역직렬화`() {
        val actual = mapper.treeToValue<Grid>(elementNode["grid"])
        println(actual)
    }

    @Test
    fun `WindCollection 부분 역직렬화`() {
        val actual = mapper.treeToValue<WindCollection>(elementNode["wind"])
        assertThat(actual.after4hour).isNull()
    }

    @Test
    fun `PrecipitationCollection 부분 역직렬화`() {
        val actual = mapper.treeToValue<PrecipitationCollection>(elementNode["precipitation"])
        assertThat(actual.after4hour).isNull()
    }

    @Test
    fun `SkyCollection 부분 역직렬화`() {
        val actual = mapper.treeToValue<SkyCollection>(elementNode["sky"])
        println(actual)
    }

    @Test
    fun `Temperature 부분 역직렬화`() {
        val actual = mapper.treeToValue<Temperature>(elementNode["temperature"])
        println(actual)
    }

    @Test
    fun `Humidity 부분 역직렬화`() {
        val actual = mapper.treeToValue<Humidity>(elementNode["humidity"])
        println(actual)
    }

    @Test
    fun `Lightning 부분 역직렬화`() {
        TODO("not implemented")
    }
}