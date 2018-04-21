package com.github.galcyurio.weathor.sk.weather

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
object SkWeatherClient {
    /**
     * SK planet 서비스의 Location API 중 [Weather](https://developers.sktelecom.com/content/sktApi/view/?svcId=10073)의 `baseUrl`이다.
     */
    private const val WEATHER_BASE_URL = "https://apis.sktelecom.com/v1/weather/status"
    private lateinit var apiKey: String

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(JacksonConverterFactory.create())
        .baseUrl(WEATHER_BASE_URL)
        .build()

    fun init(apiKey: String) {
        this.apiKey = apiKey
    }

    /**
     * 동기적으로 호출한다.
     */
    fun call() {

    }

    /**
     * 비동기적으로 호출한다
     */
    fun callAsync() {

    }

}