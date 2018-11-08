package com.github.galcyurio.weathor.sk.weatherplanet.retrofit

import com.github.galcyurio.weathor.sk.weatherplanet.SkWeatherPlanetClient
import com.github.galcyurio.weathor.sk.weatherplanet.data.CurrentWeatherMinutely
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author galcyurio
 */
interface WeatherPlanetRequest {
    @GET("current/minutely")
    fun currentMinutely(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<CurrentWeatherMinutely>

    @GET("current/minutely")
    fun currentMinutely(
        @Query("city") city: String,
        @Query("county") county: String,
        @Query("village") village: String,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<CurrentWeatherMinutely>

    @GET("current/minutely")
    fun currentMinutely(
        @Query("stnid") stnId: Int,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<CurrentWeatherMinutely>
}