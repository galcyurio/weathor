package com.github.galcyurio.weathor.sk.weatherplanet

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.treeToValue
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days.*
import com.github.galcyurio.weathor.sk.weatherplanet.support.Injector
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author galcyurio
 */
class ForecastWeather3DaysDeserializerTest {

    private val mapper = Injector.provideObjectMapper()
    private lateinit var rootNode: JsonNode
    private lateinit var elementNode: JsonNode
    private lateinit var fcst3hourNode: JsonNode
    private lateinit var fcstDailyNode: JsonNode
    private lateinit var fcst6hourNode: JsonNode

    @Before
    fun setUp() {
        val inputStream = javaClass.classLoader.getResourceAsStream("mock/forecast-3days.json")
        rootNode = mapper.readTree(inputStream)
        elementNode = rootNode["weather"]["forecast3days"][0]
        fcst3hourNode = elementNode["fcst3hour"]
        fcst6hourNode = elementNode["fcst6hour"]
        fcstDailyNode = elementNode["fcstdaily"]
    }

    @Test
    fun `전체 역직렬화`() {
        val actual: ForecastWeather3Days = mapper.treeToValue(rootNode)
        assertThat(actual).hasNoNullFieldsOrProperties()
    }

    @Test
    fun `Element 부분 역직렬화`() {
        val actual: Element = mapper.treeToValue(elementNode)
        assertThat(actual).hasNoNullFieldsOrProperties()
    }

    @Test
    fun `WindCollection 부분 역직렬화`() {
        val actual: WindCollection = mapper.treeToValue(fcst3hourNode["wind"])
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after67hour")
    }

    @Test
    fun `PrecipitationCollection 부분 역직렬화`() {
        val actual: PrecipitationCollection = mapper.treeToValue(fcst3hourNode["precipitation"])
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after67hour")
    }

    @Test
    fun `SkyCollection 부분 역직렬화`() {
        val actual: SkyCollection = mapper.treeToValue(fcst3hourNode["sky"])
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after67hour")
    }

    @Test
    fun `TemperatureCollection 부분 역직렬화`() {
        val actual: TemperatureCollection = mapper.treeToValue(fcst3hourNode["temperature"])
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after67hour")
    }

    @Test
    fun `HumidityCollection 부분 역직렬화`() {
        val actual: HumidityCollection = mapper.treeToValue(fcst3hourNode["humidity"])
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("after67hour")
    }

    @Test
    fun `RainCollection 부분 역직렬화`() {
        val actual: RainCollection = mapper.treeToValue(fcst6hourNode)
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept()
    }

    @Test
    fun `SnowCollection 부분 역직렬화`() {
        val actual: SnowCollection = mapper.treeToValue(fcst6hourNode)
        assertThat(actual).hasNoNullFieldsOrProperties()
    }

    @Test
    fun `TemperatureMinMaxCollection 부분 역직렬화`() {
        val actual: TemperatureMinMaxCollection = mapper.treeToValue(fcstDailyNode["temperature"])
        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("min1day", "max1day")
    }
}