package com.github.galcyurio.weathor.sk.weatherplanet

import com.github.galcyurio.weathor.sk.weatherplanet.retrofit.WeatherPlanetRequest
import com.github.galcyurio.weathor.sk.weatherplanet.support.EmptyApiKeyException
import com.github.galcyurio.weathor.sk.weatherplanet.support.Injector

/**
 * @author galcyurio
 */
class SkWeatherPlanetClient private constructor(
    val request: WeatherPlanetRequest
) {
    companion object {
        /**
         * SK planet 서비스의 Location API 중 [Weather Planet](https://developers.sktelecom.com/content/sktApi/view/?svcId=10113)의 `baseUrl`이다.
         */
        const val WEATHER_PLANET_BASE_URL = "https://api2.sktelecom.com/weather/"
    }

    class Builder {
        private var apiKey: String? = null
        private var baseUrl: String = WEATHER_PLANET_BASE_URL

        fun build(): SkWeatherPlanetClient {
            val retrofit = Injector.provideRetrofit(
                apiKey = apiKey ?: throw EmptyApiKeyException(),
                baseUrl = baseUrl)
            val request = retrofit.create(WeatherPlanetRequest::class.java)
            return SkWeatherPlanetClient(request)
        }

        fun apiKey(apiKey: String): Builder = apply { this.apiKey = apiKey }

        fun baseUrl(baseUrl: String): Builder = apply { this.baseUrl = baseUrl }
    }

}