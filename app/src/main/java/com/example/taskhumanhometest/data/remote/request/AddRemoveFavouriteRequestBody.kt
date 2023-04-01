package com.example.taskhumanhometest.data.remote.request

/**
 * Created by Sahil Salgotra on 01/04/23 12:20 PM
 */
data class AddRemoveFavouriteRequestBody(
    val skillName: String,
    val dictionaryName: String? = null
)
