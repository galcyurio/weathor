package com.github.galcyurio.weathor.sk.weather

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.*
import java.io.IOException

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

    private val objectMapper = ObjectMapper()
    private val client = OkHttpClient()
    private val request = Request.Builder()
        .get()
        .addHeader("TDCProjectKey", apiKey)
        .url(WEATHER_BASE_URL)
        .build()

    fun init(apiKey: String) {
        this.apiKey = apiKey
    }

    /**
     * 동기적으로 호출한다.
     */
    fun call() {
        val response = client.newCall(request).execute()
    }

    /**
     * 비동기적으로 호출한다
     */
    fun callAsync() {
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    val responseBody = response.body()!!
                    val rawBody = responseBody.string()


                    TODO()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented")
            }

        })
    }

}