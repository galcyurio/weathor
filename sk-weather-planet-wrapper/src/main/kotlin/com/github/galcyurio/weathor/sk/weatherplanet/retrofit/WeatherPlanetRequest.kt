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
    /**
     * @param latitude 위도
     * @param longitude 경도
     * @param version SK planet API 버전, 기본값은 [SkWeatherPlanetClient.API_VERSION]
     * @return 1분 단위 현재날씨 정보
     */
    @GET("current/minutely")
    fun currentMinutely(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<CurrentWeatherMinutely>

    /**
     * @param city 시 or 도
     * @param county 시 or 군 or 구
     * @param village 읍 or 면 or 동
     * @param version SK planet API 버전, 기본값은 [SkWeatherPlanetClient.API_VERSION]
     * @return 1분 단위 현재날씨 정보
     */
    @GET("current/minutely")
    fun currentMinutely(
        @Query("city") city: String,
        @Query("county") county: String,
        @Query("village") village: String,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<CurrentWeatherMinutely>

    /**
     * @param stnId 관측소 지점번호
     * @param version SK planet API 버전, 기본값은 [SkWeatherPlanetClient.API_VERSION]
     * @return 1분 단위 현재날씨 정보
     */
    @GET("current/minutely")
    fun currentMinutely(
        @Query("stnid") stnId: Int,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<CurrentWeatherMinutely>

    /**
     * @param latitude 위도
     * @param longitude 경도
     * @param version SK planet API 버전, 기본값은 [SkWeatherPlanetClient.API_VERSION]
     * @return 1시간 단위 현재날씨 정보
     */
    @GET("current/hourly")
    fun currentHourly(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<CurrentWeatherHourly>

    /**
     * @param city 시 or 도
     * @param county 시 or 군 or 구
     * @param village 읍 or 면 or 동
     * @param version SK planet API 버전, 기본값은 [SkWeatherPlanetClient.API_VERSION]
     * @return 1시간 단위 현재날씨 정보
     */
    @GET("current/hourly")
    fun currentHourly(
        @Query("city") city: String,
        @Query("county") county: String,
        @Query("village") village: String,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<CurrentWeatherHourly>

    /**
     * @param latitude 위도
     * @param longitude 경도
     * @param version SK planet API 버전, 기본값은 [SkWeatherPlanetClient.API_VERSION]
     * @return 4시간까지의 초단기예보 정보
     */
    @GET("forecast/3hours")
    fun forecast3Hours(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<ForecastWeather3Hours>

    /**
     * @param city 시 or 도
     * @param county 시 or 군 or 구
     * @param village 읍 or 면 or 동
     * @param version SK planet API 버전, 기본값은 [SkWeatherPlanetClient.API_VERSION]
     * @return 4시간까지의 초단기예보 정보
     */
    @GET("forecast/3hours")
    fun forecast3Hours(
        @Query("city") city: String,
        @Query("county") county: String,
        @Query("village") village: String,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<ForecastWeather3Hours>

    /**
     * @param latitude 위도
     * @param longitude 경도
     * @param version SK planet API 버전, 기본값은 [SkWeatherPlanetClient.API_VERSION]
     * @return 4시간부터 최대 67시간(3일)의 단기예보 정보
     */
    @GET("forecast/3days")
    fun forecast3Days(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<ForecastWeather3Days>

    /**
     * @param city 시 or 도
     * @param county 시 or 군 or 구
     * @param village 읍 or 면 or 동
     * @param version SK planet API 버전, 기본값은 [SkWeatherPlanetClient.API_VERSION]
     * @return 4시간부터 최대 67시간(3일)의 단기예보 정보
     */
    @GET("forecast/3days")
    fun forecast3Days(
        @Query("city") city: String,
        @Query("county") county: String,
        @Query("village") village: String,
        @Query("version") version: Int = SkWeatherPlanetClient.API_VERSION
    ): Call<ForecastWeather3Days>
}