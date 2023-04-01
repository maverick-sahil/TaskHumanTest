package com.example.taskhumanhometest.data.remote

/**
 * Created by Sahil Salgotra on 01/04/23 12:22 PM
 */
data class AddRemoveFavouriteDto(
    val success: Boolean,
    val message: String,
    val showFavoriteToast: Boolean?
)
