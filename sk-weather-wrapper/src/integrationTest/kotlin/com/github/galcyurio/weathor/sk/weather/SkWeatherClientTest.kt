package com.github.galcyurio.weathor.sk.weather

import com.github.galcyurio.weathor.sk.weather.data.SkWeatherStatus
import com.github.galcyurio.weathor.sk.weather.support.SkWeatherCallbackAdapter
import org.assertj.core.api.Assertions.assertThat
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * @author galcyurio
 */
class SkWeatherClientTest {

    companion object {
        lateinit var client: SkWeatherClient
        const val latitude = 37.5660649
        const val longitude = 126.9826791

        @JvmStatic @BeforeClass
        fun setUp() {
            val inputStream = SkWeatherClientTest::class.java.classLoader.getResourceAsStream("secret.properties")
            val apiKey = Properties().run {
                load(inputStream)
                getProperty("apiKey")
            }

            client = SkWeatherClient.Builder()
                .apiKey(apiKey)
                .build()
        }
    }

    @Test
    fun `Weather API 서버에 동기적으로 요청`() {
        val skWeatherStatus = client.call(latitude, longitude).body()
        assertThat(skWeatherStatus).isNotNull()
        assertThat(skWeatherStatus).hasNoNullFieldsOrProperties()
        println(skWeatherStatus)
    }

    @Test
    fun `Weather API 서버에 비동기적으로 요청`() {
        val lock = CountDownLatch(1)
        client.callAsync(latitude, longitude, object : SkWeatherCallbackAdapter<SkWeatherStatus> {
            override fun onSuccess(call: Call<SkWeatherStatus>, response: Response<SkWeatherStatus>) {
                val skWeatherStatus = response.body()
                assertThat(skWeatherStatus).isNotNull()
                assertThat(skWeatherStatus).hasNoNullFieldsOrProperties()
                println(skWeatherStatus)
                lock.countDown()
            }
        })
        lock.await(1, TimeUnit.SECONDS)
    }
}