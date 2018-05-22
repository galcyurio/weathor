package weatherplanet

import com.github.galcyurio.weathor.sk.weatherplanet.SkWeatherPlanetClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.BeforeClass
import org.junit.Test
import java.util.*

/**
 * @author galcyurio
 */
class SkWeatherPlanetClientTest {
    companion object {
        lateinit var client: SkWeatherPlanetClient

        @JvmStatic @BeforeClass
        fun setUp() {
            val inputStream = SkWeatherPlanetClientTest::class.java.classLoader.getResourceAsStream("secret.properties")
            val properties = Properties()
            properties.load(inputStream)
            val apiKey = properties.getProperty("apiKey")

            client = SkWeatherPlanetClient.Builder()
                .apiKey(apiKey)
                .build()
        }
    }

    @Test
    fun `SkWeatherPlanetClient 초기화`() {
        assertThat(client).isNotNull()
    }
}