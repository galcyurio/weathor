package com.github.galcyurio.weathor.sk.weatherplanet.support

class EmptyApiKeyException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)