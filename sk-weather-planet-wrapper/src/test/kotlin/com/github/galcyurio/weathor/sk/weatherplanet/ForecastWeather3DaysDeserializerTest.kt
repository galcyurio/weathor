package com.github.galcyurio.weathor.sk.weatherplanet

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.treeToValue
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days.*
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Precipitation
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.Sky
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
        mapper.treeToValue<ForecastWeather3Days>(rootNode)
    }

    @Test
    fun `Element 부분 역직렬화`() {
        mapper.treeToValue<Element>(elementNode)
    }

    @Test
    fun `WindCollection 부분 역직렬화`() {
        val actual: WindCollection = mapper.treeToValue(fcst3hourNode["wind"])

        assertThat(actual.after4hour?.direction).isEqualTo(248.00f)
        assertThat(actual.after4hour?.speed).isEqualTo(1.60f)
    }

    @Test
    fun `PrecipitationProbabilityCollection 부분 역직렬화`() {
        val actual: PrecipitationProbabilityCollection = mapper.treeToValue(fcst3hourNode["precipitation"])

        assertThat(actual.after67hour).isNull()
        assertThat(actual.after58hour?.type).isEqualTo(Precipitation.Type.RAIN)
        assertThat(actual.after58hour?.percentage).isEqualTo(60f)
    }

    @Test
    fun `SkyCollection 부분 역직렬화`() {
        val actual: SkyCollection = mapper.treeToValue(fcst3hourNode["sky"])
        assertThat(actual.after19hour).isEqualTo(Sky.SKY_S02)
    }

    @Test
    fun `TemperatureCollection 부분 역직렬화`() {
        val actual: TemperatureCollection = mapper.treeToValue(fcst3hourNode["temperature"])
        assertThat(actual.after4hour).isEqualTo(27.00f)
    }

    @Test
    fun `HumidityCollection 부분 역직렬화`() {
        val actual: HumidityCollection = mapper.treeToValue(fcst3hourNode["humidity"])
        assertThat(actual.after4hour).isEqualTo(35.00f)
    }

    @Test
    fun `RainCollection 부분 역직렬화`() {
        val actual: RainCollection = mapper.treeToValue(fcst6hourNode)
        assertThat(actual.after60hour).isEqualTo(Rain.BETWEEN_1_4_MM)
    }

    @Test
    fun `SnowCollection 부분 역직렬화`() {
        val actual: SnowCollection = mapper.treeToValue(fcst6hourNode)
        assertThat(actual.after6hour).isEqualTo(Snow.NONE)
    }

    @Test
    fun `TemperatureMinMaxCollection 부분 역직렬화`() {
        val actual: TemperatureMinMaxCollection = mapper.treeToValue(fcstDailyNode["temperature"])
        assertThat(actual.min1day).isNull()
        assertThat(actual.max1day).isEqualTo(30.00f)
    }
}