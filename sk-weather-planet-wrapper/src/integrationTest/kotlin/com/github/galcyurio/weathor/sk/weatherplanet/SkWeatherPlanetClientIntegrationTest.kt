package com.github.galcyurio.weathor.sk.weatherplanet

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * @author galcyurio
 */
class SkWeatherPlanetClientIntegrationTest {

    private val apiKey: String
    private val latitude = 36.1234
    private val longitude = 127.1234
    private val city = "서울"
    private val county = "강남구"
    private val village = "삼성동"
    private val stnid = 108

    private lateinit var client: SkWeatherPlanetClient

    init {
        val inputStream = javaClass.classLoader.getResourceAsStream("secret.properties")
        apiKey = Properties().run {
            load(inputStream)
            getProperty("apiKey")
        }
    }

    @Before
    fun setUp() {
        client = SkWeatherPlanetClient.Builder()
            .apiKey(apiKey)
            .build()
    }

    @Test
    fun `위경도로 currentMinutely() 호출`() {
        val actual = client.currentMinutely(latitude, longitude).execute().body()
        assertThat(actual).isNotNull()
    }

    @Test
    fun `주소로 currentMinutely() 호출`() {
        val actual = client.currentMinutely(city, county, village).execute().body()
        assertThat(actual).isNotNull()
    }

    @Test
    fun `관측소 ID로 currentMinutely() 호출`() {
        val actual = client.currentMinutely(stnid).execute().body()
        assertThat(actual).isNotNull()
    }

    @Test
    fun `위경도로 currentHourly() 호출`() {
        val actual = client.currentHourly(latitude, longitude).execute().body()
        assertThat(actual).isNotNull()
    }

    @Test
    fun `주소로 currentHourly() 호출`() {
        val actual = client.currentHourly(city, county, village).execute().body()
        assertThat(actual).isNotNull()
    }

    @Test
    fun `위경도로 forecast3Hours() 호출`() {
        val actual = client.forecast3Hours(latitude, longitude).execute().body()
        assertThat(actual).isNotNull()
    }

    @Test
    fun `주소로 forecast3Hours() 호출`() {
        val actual = client.forecast3Hours(city, county, village).execute().body()
        assertThat(actual).isNotNull()
    }

    @Test
    fun `위경도로 forecast3Days() 호출`() {
        val actual = client.forecast3Days(latitude, longitude).execute().body()
        assertThat(actual).isNotNull()
    }

    @Test
    fun `주소로 forecast3Days() 호출`() {
        val actual = client.forecast3Days(city, county, village).execute().body()
        assertThat(actual).isNotNull()
    }
}