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
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * @author galcyurio
 */
class SkWeatherClientTest {

    private val json = File(this.javaClass.classLoader.getResource("mock/WeatherStatus.json").toURI()).readText()
    private val latitude = 37.5660649
    private val longitude = 126.9826791

    private lateinit var server: MockWebServer
    private lateinit var client: SkWeatherClient
    private lateinit var lock: CountDownLatch

    @Before
    fun setUp() {
        server = MockWebServer()
        server.enqueue(MockResponse().apply {
            setResponseCode(200)
            setBody(json)
        })

        client = SkWeatherClient.Builder()
            .apiKey("TEST")
            .baseUrl(server.url(""))
            .build()

        lock = CountDownLatch(1)
    }

    @Test
    fun `동기적인 호출 후에 리턴값으로 VO class 반환`() {
        val skWeatherStatus = client.call(latitude, longitude).body()

        assertThat(skWeatherStatus).hasNoNullFieldsOrProperties()
        server.close()
    }

    @Test
    fun `비동기적인 호출 후에 callback 함수로 VO class 간접적으로 반환`() {
        client.callAsync(latitude, longitude, object : SkWeatherCallbackAdapter<SkWeatherStatus> {
            override fun onSuccess(call: Call<SkWeatherStatus>, response: Response<SkWeatherStatus>) {
                val skWeatherStatus = response.body()
                assertThat(skWeatherStatus).hasNoNullFieldsOrProperties()
                lock.countDown()
            }
        })

        lock.await(2, TimeUnit.SECONDS)
        server.close()
    }
}