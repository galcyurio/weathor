package com.github.galcyurio.weathor.sk.weatherplanet

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * @author galcyurio
 */
class SkWeatherPlanetClient private constructor(
    val retrofit: Retrofit
) {
    companion object {
        /**
         * SK planet 서비스의 Location API 중 [Weather Planet](https://developers.sktelecom.com/content/sktApi/view/?svcId=10113)의 `baseUrl`이다.
         */
        const val WEATHER_PLANET_BASE_URL = "https://api2.sktelecom.com/weather/"
    }

    class Builder {
        private var apiKey: String? = null
        private var baseUrl: HttpUrl = HttpUrl.parse(WEATHER_PLANET_BASE_URL)!!

        fun build(): SkWeatherPlanetClient = SkWeatherPlanetClient(Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()
                .registerKotlinModule()))
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder()
                        .addHeader("TDCProjectKey", apiKey!!)
                        .build())
                }
                .build()
            )
            .build())

        fun apiKey(apiKey: String): Builder = apply { this.apiKey = apiKey }

        fun baseUrl(baseUrl: HttpUrl): Builder = apply { this.baseUrl = baseUrl }
    }

}