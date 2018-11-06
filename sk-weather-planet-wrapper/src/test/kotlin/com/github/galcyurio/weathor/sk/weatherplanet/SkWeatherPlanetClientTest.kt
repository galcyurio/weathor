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
}