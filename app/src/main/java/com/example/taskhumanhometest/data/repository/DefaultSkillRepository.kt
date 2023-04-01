package com.example.taskhumanhometest.data.repository

import com.example.taskhumanhometest.data.remote.AddRemoveFavouriteDto
import com.example.taskhumanhometest.data.remote.SkillsApi
import com.example.taskhumanhometest.data.remote.SkillsDto
import com.example.taskhumanhometest.data.remote.request.AddRemoveFavouriteRequestBody
import com.example.taskhumanhometest.data.util.SessionManager
import com.example.taskhumanhometest.domain.repository.SkillRepository
import com.example.taskhumanhometest.domain.util.Resource
import javax.inject.Inject

/**
 * Created by Sahil Salgotra on 01/04/23 12:37 PM
 */
class DefaultSkillRepository @Inject constructor(
    private val api: SkillsApi,
    private val sessionManager: SessionManager
) : SkillRepository {
    override suspend fun getSkillData(): Resource<SkillsDto> {
        return try {
            Resource.Success(
                api.getSkillsData(sessionManager.fetchAuthToken())
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            Resource.Error(ex.message ?: "Unknown error")
        }
    }

    override suspend fun addFavourite(skillName: String, dictionaryName: String): Resource<AddRemoveFavouriteDto> {
        return try {
            Resource.Success(
                api.addFavourite(
                    sessionManager.fetchAuthToken(),
                    AddRemoveFavouriteRequestBody(skillName = skillName, dictionaryName = dictionaryName)
                )
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            Resource.Error(ex.message ?: "Unknown error")
        }
    }

    override suspend fun removeFavourite(skillName: String): Resource<AddRemoveFavouriteDto> {
        return try {
            Resource.Success(
                api.removeFavourite(
                    sessionManager.fetchAuthToken(),
                    AddRemoveFavouriteRequestBody(skillName = skillName)
                )
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            Resource.Error(ex.message ?: "Unknown error")
        }
    }
}