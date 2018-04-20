package weatherplanet

import com.github.galcyurio.weathor.weatherplanet.WeatherPlanetClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * @author galcyurio
 */
class WeatherPlanetClientTest {

    @Test
    fun `싱글턴 object 인스턴스화`() {
        assertThat(WeatherPlanetClient).isNotNull()
    }
}