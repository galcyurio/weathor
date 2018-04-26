package com.github.galcyurio.weathor.sk.weather.retrofit

import com.github.galcyurio.weathor.sk.weather.data.SkWeatherStatus
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author galcyurio
 */
interface SkWeatherRequest {

    @GET("weather/status")
    fun weatherStatus(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Call<SkWeatherStatus>
}