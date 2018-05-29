package com.github.galcyurio.weathor.sk.weatherplanet

import org.assertj.core.api.Assertions.assertThat
import org.junit.BeforeClass
import org.junit.Test

/**
 * @author galcyurio
 */
class SkWeatherPlanetClientTest {
    companion object {
        lateinit var client: SkWeatherPlanetClient

        @JvmStatic @BeforeClass
        fun setUp() {
            client = SkWeatherPlanetClient.Builder()
                .apiKey("dummyApiKey")
                .build()
        }
    }

    @Test
    fun `SkWeatherPlanetClient 초기화`() {
        assertThat(client).isNotNull()
    }
}