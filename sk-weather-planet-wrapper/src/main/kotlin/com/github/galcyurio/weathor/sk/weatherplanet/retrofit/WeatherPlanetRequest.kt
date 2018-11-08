package com.github.galcyurio.weathor.sk.weatherplanet.retrofit

import com.github.galcyurio.weathor.sk.weatherplanet.SkWeatherPlanetClient
import com.github.galcyurio.weathor.sk.weatherplanet.data.CurrentWeatherHourly
import com.github.galcyurio.weathor.sk.weatherplanet.data.CurrentWeatherMinutely
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Days
import com.github.galcyurio.weathor.sk.weatherplanet.data.ForecastWeather3Hours
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

    @GET("current/hourly")
    fun currentHourly(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<CurrentWeatherHourly>

    @GET("current/hourly")
    fun currentHourly(
        @Query("city") city: String,
        @Query("county") county: String,
        @Query("village") village: String,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<CurrentWeatherHourly>

    @GET("forecast/3hours")
    fun forecast3Hours(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<ForecastWeather3Hours>

    @GET("forecast/3hours")
    fun forecast3Hours(
        @Query("city") city: String,
        @Query("county") county: String,
        @Query("village") village: String,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<ForecastWeather3Hours>

    @GET("forecast/3days")
    fun forecast3Days(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<ForecastWeather3Days>

    @GET("forecast/3days")
    fun forecast3Days(
        @Query("city") city: String,
        @Query("county") county: String,
        @Query("village") village: String,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<ForecastWeather3Days>
}