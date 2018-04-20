package weatherplanet

import com.github.galcyurio.weathor.sk.weatherplanet.SkWeatherPlanetClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * @author galcyurio
 */
class SkWeatherPlanetClientTest {

    @Test
    fun `싱글턴 object 인스턴스화`() {
        assertThat(SkWeatherPlanetClient).isNotNull()
    }
}