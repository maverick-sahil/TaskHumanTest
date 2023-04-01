package com.example.taskhumanhometest.domain.repository

import com.example.taskhumanhometest.data.remote.AddRemoveFavouriteDto
import com.example.taskhumanhometest.data.remote.SkillsDto
import com.example.taskhumanhometest.domain.util.Resource

/**
 * Created by Sahil Salgotra on 01/04/23 12:29 PM
 */
interface SkillRepository {
    suspend fun getSkillData(): Resource<SkillsDto>

    suspend fun addFavourite(skillName: String, dictionaryName: String): Resource<AddRemoveFavouriteDto>

    suspend fun removeFavourite(skillName: String): Resource<AddRemoveFavouriteDto>
}