package com.github.galcyurio.weathor.sk.weather

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.galcyurio.weathor.sk.weather.data.SkWeatherStatus
import com.github.galcyurio.weathor.sk.weather.retrofit.SkWeatherRequest
import com.github.galcyurio.weathor.sk.weather.support.SkWeatherStatusDeserializer
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Weather API는 누적된 날씨 데이터와 문장형으로 전달되는 콘텐츠의
 * 내용 분석 데이터를 Mapping하여 수치화된 날씨 정보를 사용자가 체감하고
 * 이해하는 언어 기반의 감성 정보로 자동 변환하여 드립니다.
 *
 * [공식문서](https://developers.sktelecom.com/content/sktApi/view/?svcId=10073)
 *
 * @author galcyurio
 */
class SkWeatherClient private constructor(
    private val retrofit: Retrofit
) {
    companion object {
        /**
         * SK planet 서비스의 Location API 중 [Weather](https://developers.sktelecom.com/content/sktApi/view/?svcId=10073)의 `baseUrl`이다.
         */
        const val WEATHER_BASE_URL = "https://apis.sktelecom.com/v1/"
    }

    private val request: SkWeatherRequest by lazy { retrofit.create(SkWeatherRequest::class.java) }

    /**
     * 동기적으로 호출한다.
     * @param latitude 위도
     * @param longitude 경도
     */
    fun call(latitude: Double, longitude: Double): Response<SkWeatherStatus> {
        return request.weatherStatus(latitude, longitude).execute()
    }

    /**
     * 비동기적으로 호출한다
     */
    fun callAsync(latitude: Double, longitude: Double, callback: Callback<SkWeatherStatus>) {
        request.weatherStatus(latitude, longitude).enqueue(callback)
    }

    class Builder {
        private var apiKey: String? = null
        private var baseUrl: HttpUrl = HttpUrl.parse(WEATHER_BASE_URL)!!

        fun build(): SkWeatherClient = SkWeatherClient(Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()
                .registerKotlinModule()
                .registerModule(SimpleModule()
                    .addDeserializer(SkWeatherStatus::class.java, SkWeatherStatusDeserializer()))))
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