package com.github.galcyurio.weathor.sk.weatherplanet

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @author galcyurio
 */
class SkWeatherPlanetClientTest {

    private lateinit var client: SkWeatherPlanetClient
    private lateinit var server: MockWebServer
    private lateinit var response: MockResponse

    private val readJson: (String) -> String = { javaClass.classLoader.getResource(it).readText() }
    private val currentWeatherMinutelyJson = readJson("mock/current-weather-minutely.json")
    private val currentWeatherHourlyJson = readJson("mock/current-weather-hourly.json")
    private val forecastWeather3HoursJson = readJson("mock/forecast-3hours.json")
    private val forecastWeahter3DaysJson = readJson("mock/forecast-3days.json")

    @Before
    fun setUp() {
        server = MockWebServer()
        response = MockResponse()

        client = SkWeatherPlanetClient.Builder()
            .apiKey("dummyApiKey")
            .baseUrl(server.url("").toString())
            .build()
    }

    @After
    fun tearDown() {
        server.close()
    }

    @Test
    fun `SkWeatherPlanetClient 초기화`() {
        assertThat(client).isNotNull()
    }

    @Test
    fun `currentMinutely 호출`() {
        response.setBody(currentWeatherMinutelyJson)
        (1..3).forEach { server.enqueue(response) }

        val actual1 = client.currentMinutely(1.0, 1.0).execute().body()
        val actual2 = client.currentMinutely("", "", "").execute().body()
        val actual3 = client.currentMinutely(1).execute().body()

        assertThat(arrayOf(actual1, actual2, actual3)).doesNotContainNull()
    }

    @Test
    fun `currentHourly 호출`() {
        response.setBody(currentWeatherHourlyJson)
        (1..2).forEach { server.enqueue(response) }

        val actual1 = client.currentHourly("", "", "").execute().body()
        val actual2 = client.currentHourly(1.0, 1.0).execute().body()

        assertThat(arrayOf(actual1, actual2)).doesNotContainNull()
    }

    @Test
    fun `forecast3Hours 호출`() {
        response.setBody(forecastWeather3HoursJson)
        (1..2).forEach { server.enqueue(response) }

        val actual = arrayOf(
            client.forecast3Hours("", "", "").execute().body(),
            client.forecast3Hours(1.0, 1.0).execute().body())

        assertThat(actual).doesNotContainNull()
    }

    @Test
    fun `forecast3Days 호출`() {
        response.setBody(forecastWeahter3DaysJson)
        (1..2).forEach { server.enqueue(response) }

        val actual = arrayOf(
            client.forecast3Days("", "", "").execute().body(),
            client.forecast3Days(1.0, 1.0).execute().body())

        assertThat(actual).doesNotContainNull()
    }
}