package com.example.taskhumanhometest.presentation

import com.example.taskhumanhometest.data.remote.SkillsDataDto

/**
 * Created by Sahil Salgotra on 01/04/23 1:03 PM
 */
data class SkillsState(
    val topic: String? = null,
    val skillData: List<SkillsDataDto>? = null,
    val loading: Boolean = false,
    val error: String? = null
)
