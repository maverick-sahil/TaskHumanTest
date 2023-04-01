package com.example.taskhumanhometest.domain.util

/**
 * Created by Sahil Salgotra on 01/04/23 12:30 PM
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}