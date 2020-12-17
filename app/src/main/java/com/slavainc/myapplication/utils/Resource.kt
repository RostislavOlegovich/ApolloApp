package com.slavainc.myapplication.utils

data class Resource<out T>(val status: Status, val data: T?, val exception: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(e: String?): Resource<T> {
            return Resource(Status.ERROR, null, e)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}