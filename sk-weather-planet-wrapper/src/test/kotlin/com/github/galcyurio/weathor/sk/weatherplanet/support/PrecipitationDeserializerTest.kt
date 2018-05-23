package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.galcyurio.weathor.sk.weatherplanet.data.Precipitation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author galcyurio
 */
class PrecipitationDeserializerTest {

    lateinit var mapper: ObjectMapper

    @Before
    fun setUp() {
        mapper = ObjectMapper()
            .registerKotlinModule()
            .registerModule(SimpleModule()
                .addDeserializer(Precipitation::class.java, PrecipitationDeserializer()))
    }

    @Test
    fun `json파일 역직렬화 테스트 - MILLIMETER`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/precipitation-0.json")
        val precipitation = mapper.readValue(inputStream, Precipitation::class.java)

        println(precipitation)
        assertThat(precipitation.unit).isEqualTo(Precipitation.Unit.MILLIMETER)
        assertThat(precipitation.sinceOnTime).isEqualTo(0.17f)
    }

    @Test
    fun `json파일 역직렬화 테스트 - CENTIMETER`() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("mock/precipitation-3.json")
        val precipitation = mapper.readValue(inputStream, Precipitation::class.java)

        println(precipitation)
        assertThat(precipitation.unit).isEqualTo(Precipitation.Unit.CENTIMETER)
        assertThat(precipitation.sinceOnTime).isEqualTo(0.17f)
    }
}