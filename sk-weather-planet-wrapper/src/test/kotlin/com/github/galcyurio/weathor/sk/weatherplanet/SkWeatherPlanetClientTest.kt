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
        server.enqueue(response)

        val actual1 = client.currentMinutely(1.0, 1.0).execute().body()
        val actual2 = client.currentMinutely("", "", "").execute().body()
        val actual3 = client.currentMinutely(1).execute().body()

        assertThat(arrayOf(actual1, actual2, actual3)).doesNotContainNull()
    }
}