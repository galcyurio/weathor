package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.galcyurio.weathor.sk.weatherplanet.SkWeatherPlanetClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.*

/**
 * @author galcyurio
 */
internal object Injector {
    fun provideObjectMapper(): ObjectMapper {
        return ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerKotlinModule()
            .registerModule(SimpleModule()
                .addDeserializer(Date::class.java, SkTimeDeserializer()))
    }

    fun provideOkHttpClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val newUrl = request.url().newBuilder()
                    .addQueryParameter("appKey", apiKey)
                    .build()
                chain.proceed(request.newBuilder().url(newUrl).build())
            }
            .build()
    }

    fun provideRetrofit(
        apiKey: String,
        baseUrl: String = SkWeatherPlanetClient.WEATHER_PLANET_BASE_URL,
        objectMapper: ObjectMapper = Injector.provideObjectMapper(),
        okHttpClient: OkHttpClient = Injector.provideOkHttpClient(apiKey)
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }
}