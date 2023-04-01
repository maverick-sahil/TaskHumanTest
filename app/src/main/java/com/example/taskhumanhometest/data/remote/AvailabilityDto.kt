package com.example.taskhumanhometest.data.remote

data class AvailabilityDto(
    val status: Int,
    val color: String,
    val startTime: Long,
    val endTime: Long
)
