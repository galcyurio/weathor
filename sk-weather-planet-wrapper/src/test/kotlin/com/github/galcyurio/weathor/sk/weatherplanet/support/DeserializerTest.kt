package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.galcyurio.weathor.sk.weatherplanet.data.Precipitation
import com.github.galcyurio.weathor.sk.weatherplanet.data.Rain
import com.github.galcyurio.weathor.sk.weatherplanet.data.Sky
import org.assertj.core.api.Assertions
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
    fun `Precipitation json 파일 역직렬화 테스트 - MILLIMETER`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/precipitation-0.json")
        val precipitation = mapper.readValue(inputStream, Precipitation::class.java)

        println(precipitation)
        Assertions.assertThat(precipitation.unit).isEqualTo(Precipitation.Unit.MILLIMETER)
        Assertions.assertThat(precipitation.sinceOnTime).isEqualTo(0.17f)
    }

    @Test
    fun `Precipitation json 파일 역직렬화 테스트 - CENTIMETER`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/precipitation-3.json")
        val precipitation = mapper.readValue(inputStream, Precipitation::class.java)

        println(precipitation)
        Assertions.assertThat(precipitation.unit).isEqualTo(Precipitation.Unit.CENTIMETER)
        Assertions.assertThat(precipitation.sinceOnTime).isEqualTo(0.17f)
    }

    @Test
    fun `Sky json 파일 역직렬화 테스트`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/sky.json")
        val sky = mapper.readValue(inputStream, Sky::class.java)

        println(sky)
        Assertions.assertThat(sky).isEqualTo(Sky.SKY_A08)
    }

    @Test
    fun `Rain json 파일 역직렬화 테스트`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/rain.json")
        val rain = mapper.readValue(inputStream, Rain::class.java)

        println(rain)
    }
}