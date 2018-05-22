package com.github.galcyurio.weathor.commons

import retrofit2.Call
import retrofit2.Response

/**
 * [WeathorCallback]의 구현체
 * @author galcyurio
 */
open class WeathorCallbackAdapter<T> : WeathorCallback<T>() {

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