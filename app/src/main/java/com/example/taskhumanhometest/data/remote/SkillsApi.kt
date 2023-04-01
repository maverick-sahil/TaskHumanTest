package com.example.taskhumanhometest.data.remote

import retrofit2.http.GET
import retrofit2.http.Header

interface SkillsApi {

    @GET("/discover/topicDetails/physical%20fitness")
    suspend fun getSkillsData(@Header("Authorization") token: String): SkillsDto
}