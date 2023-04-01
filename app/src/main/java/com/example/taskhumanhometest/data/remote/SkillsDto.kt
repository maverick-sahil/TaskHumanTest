package com.example.taskhumanhometest.data.remote

data class SkillsDto(
    val success: Boolean,
    val topicHeader: TopicHeaderDto,
    val skills: List<SkillsDataDto>,
    val isNextPage: Boolean
)
