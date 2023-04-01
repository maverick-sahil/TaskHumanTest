package com.example.taskhumanhometest.data.remote

import com.example.taskhumanhometest.data.remote.request.AddRemoveFavouriteRequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SkillsApi {

    @GET("/discover/topicDetails/physical%20fitness")
    suspend fun getSkillsData(@Header("Authorization") token: String): SkillsDto

    @POST("/favorite/add")
    suspend fun addFavourite(
        @Header("Authorization") token: String,
        @Body requestBody: AddRemoveFavouriteRequestBody
    ): AddRemoveFavouriteDto

    @POST("favorite/remove")
    suspend fun removeFavourite(
        @Header("Authorization") token: String,
        @Body requestBody: AddRemoveFavouriteRequestBody
    ): AddRemoveFavouriteDto
}