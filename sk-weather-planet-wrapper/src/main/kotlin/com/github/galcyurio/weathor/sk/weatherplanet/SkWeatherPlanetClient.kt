package com.github.galcyurio.weathor.sk.weatherplanet

import com.github.galcyurio.weathor.sk.weatherplanet.support.WEATHER_PLANET_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * @author galcyurio
 */
object SkWeatherPlanetClient {
    lateinit var apiKey: String

    val retrofit = Retrofit.Builder()
        .baseUrl(WEATHER_PLANET_BASE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    fun init(apiKey: String) {
        this.apiKey = apiKey
    }

}