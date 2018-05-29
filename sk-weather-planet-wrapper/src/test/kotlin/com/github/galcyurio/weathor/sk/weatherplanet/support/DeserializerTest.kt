package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.galcyurio.weathor.sk.weatherplanet.data.*
import com.github.galcyurio.weathor.sk.weatherplanet.data.part.*
import org.assertj.core.api.Assertions.assertThat
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

    @Test
    fun `Precipitation json 파일 역직렬화 - MILLIMETER`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/part/precipitation-0.json")
        val precipitation = mapper.readValue(inputStream, Precipitation::class.java)

        println(precipitation)
        assertThat(precipitation.unit).isEqualTo(Precipitation.Unit.MILLIMETER)
        assertThat(precipitation.sinceOnTime).isEqualTo(0.17f)
    }

    @Test
    fun `Precipitation json 파일 역직렬화 - CENTIMETER`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/part/precipitation-3.json")
        val precipitation = mapper.readValue(inputStream, Precipitation::class.java)

        println(precipitation)
        assertThat(precipitation.unit).isEqualTo(Precipitation.Unit.CENTIMETER)
        assertThat(precipitation.sinceOnTime).isEqualTo(0.17f)
    }

    @Test
    fun `Sky json 파일 역직렬화`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/part/sky.json")
        val sky = mapper.readValue(inputStream, Sky::class.java)

        println(sky)
        assertThat(sky).isEqualTo(Sky.SKY_A08)
    }

    @Test
    fun `Rain json 파일 역직렬화`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/part/rain.json")
        val rain = mapper.readValue(inputStream, Rain::class.java)

        println(rain)
        assertThat(rain).isNotNull()
    }

    @Test
    fun `Pressure json 파일 역직렬화`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/part/pressure.json")
        val pressure = mapper.readValue(inputStream, Pressure::class.java)

        println(pressure)
        assertThat(pressure).isNotNull()
    }

    @Test
    fun `Common json 파일 역직렬화`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/part/common.json")
        val common = mapper.readValue(inputStream, Common::class.java)

        println(common)
        assertThat(common).isNotNull()
    }

    @Test
    fun `Result json 파일 역직렬화`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/part/result.json")
        val result = mapper.readValue(inputStream, Result::class.java)

        println(result)
        assertThat(result).isNotNull()
    }
}