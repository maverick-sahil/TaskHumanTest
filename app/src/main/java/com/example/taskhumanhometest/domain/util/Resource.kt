package com.example.taskhumanhometest.domain.util

/**
 * Created by Sahil Salgotra on 01/04/23 12:30 PM
 */
// A sealed class that represents the result of an operation that can either succeed or fail.
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    // A class that represents a successful operation with a nullable data object.
    class Success<T>(data: T?) : Resource<T>(data)

    // A class that represents a failed operation with an error message and a nullable data object.
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}