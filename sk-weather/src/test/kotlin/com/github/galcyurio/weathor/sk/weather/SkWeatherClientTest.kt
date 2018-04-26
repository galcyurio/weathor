package com.github.galcyurio.weathor.sk.weather

import com.github.galcyurio.weathor.sk.weather.data.SkWeatherStatus
import com.github.galcyurio.weathor.sk.weather.support.SkWeatherCallbackAdapter
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.io.File

/**
 * @author galcyurio
 */
class SkWeatherClientTest {

    lateinit var server: MockWebServer
    val json = File(this.javaClass.classLoader.getResource("mock/WeatherStatus.json").toURI()).readText()

    @Before
    fun setUp() {
        server = MockWebServer()
        server.enqueue(MockResponse().apply {
            setResponseCode(200)
            setBody(json)
        })

        SkWeatherClient.init("TEST")
    }

    @Test
    fun `동기적인 호출 후에 리턴값으로 VO class 반환`() {
        val skWeatherStatus = SkWeatherClient.call().body()
        assertThat(skWeatherStatus).isNotNull()

        server.close()
    }

    @Test
    fun `비동기적인 호출 후에 callback 함수로 VO class 간접적으로 반환`() {
        server.takeRequest()

        SkWeatherClient.callAsync(object : SkWeatherCallbackAdapter<SkWeatherStatus> {
            override fun onSuccess(call: Call<SkWeatherStatus>, response: Response<SkWeatherStatus>) {
                val skWeatherStatus = response.body()
                assertThat(skWeatherStatus).isNotNull()
            }
        })

        server.close()
    }
}