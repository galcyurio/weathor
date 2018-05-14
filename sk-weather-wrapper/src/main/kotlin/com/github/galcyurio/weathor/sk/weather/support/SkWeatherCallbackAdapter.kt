package com.github.galcyurio.weathor.sk.weather.support

import retrofit2.Call
import retrofit2.Response

/**
 * [SkWeatherCallback]의 구현체
 * @author galcyurio
 */
open class SkWeatherCallbackAdapter<T> : SkWeatherCallback<T>() {

    override fun onSuccess(call: Call<T>, response: Response<T>) {
        /* empty */
    }

    override fun onResponseError(call: Call<T>, response: Response<T>) {
        /* empty */
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        /* empty */
    }
}