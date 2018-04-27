package com.github.galcyurio.weathor.sk.weather.support

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author galcyurio
 */
interface SkWeatherCallback<T> : Callback<T> {

    /**
     * 응답을 받았으며 [Response.isSuccessful] 값이 `true`인 경우 (200 ~ 299)
     */
    fun onSuccess(call: Call<T>, response: Response<T>)

    /**
     * 응답을 받았으나 [Response.isSuccessful] 값이 `false`인 경우
     */
    fun onResponseError(call: Call<T>, response: Response<T>)

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(call, response)
        } else {
            onResponseError(call, response)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable)
}