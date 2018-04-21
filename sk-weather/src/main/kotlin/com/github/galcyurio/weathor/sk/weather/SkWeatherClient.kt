package com.github.galcyurio.weathor.sk.weather

import com.github.galcyurio.weathor.sk.weather.data.RawSkWeatherStatus
import com.github.galcyurio.weathor.sk.weather.data.SkWeatherStatus
import com.github.galcyurio.weathor.sk.weather.retrofit.SkWeatherRequest
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
object SkWeatherClient {
    /**
     * SK planet 서비스의 Location API 중 [Weather](https://developers.sktelecom.com/content/sktApi/view/?svcId=10073)의 `baseUrl`이다.
     */
    private const val WEATHER_BASE_URL = "https://apis.sktelecom.com/v1/weather/status"
    private lateinit var apiKey: String

    private lateinit var request: SkWeatherRequest

    fun init(apiKey: String) {
        this.apiKey = apiKey

        request = Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl(WEATHER_BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder()
                        .addHeader("TDCProjectKey", this.apiKey)
                        .build())
                }
                .build()
            )
            .build()
            .create(SkWeatherRequest::class.java)
    }

    /**
     * 동기적으로 호출한다.
     */
    fun call(): Response<RawSkWeatherStatus> {
        return request.weatherStatus().execute()
    }

    /**
     * 비동기적으로 호출한다
     */
    fun callAsync(callback: Callback<SkWeatherStatus>) {
        TODO("Jackson Deserializer 만들기")
        // TODO: Jackson Deserializer를 만들어 본 뒤에 Raw 객체를 쓸 것인지 결정할 것
    }

}