package com.slavainc.utils

import com.apollographql.apollo.api.Response

fun <T, R> Response<T>.handle(block: ((T?) -> R?)? = null): ResponseWrapper<R> = if (hasErrors() || this.data == null) {
    ResponseWrapper(error = this.errors?.firstOrNull()?.message ?: "")
} else {
    val data = this.data
    val mappedData = block?.invoke(data)
    ResponseWrapper(data = mappedData)
}