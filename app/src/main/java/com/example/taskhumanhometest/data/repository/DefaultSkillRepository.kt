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
/**
 * Default implementation of [SkillRepository] interface which interacts with [SkillsApi] to fetch skills data
 * and perform add/remove favourite actions.
 *
 * @property api instance of [SkillsApi] used for making network requests.
 * @property sessionManager instance of [SessionManager] used for fetching authentication token.
 */
class DefaultSkillRepository @Inject constructor(
    private val api: SkillsApi,
    private val sessionManager: SessionManager
) : SkillRepository {

    /**
     * Fetches skills data from the [SkillsApi] using authentication token from [SessionManager].
     * Returns [Resource.Success] with the fetched data or [Resource.Error] with the exception message.
     */
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

    /**
     * Adds a skill to favourites using [SkillsApi] and authentication token from [SessionManager].
     * Returns [Resource.Success] with the response data or [Resource.Error] with the exception message.
     *
     * @param skillName name of the skill to add to favourites.
     * @param dictionaryName name of the dictionary containing the skill.
     */
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

    /**
     * Removes a skill from favourites using [SkillsApi] and authentication token from [SessionManager].
     * Returns [Resource.Success] with the response data or [Resource.Error] with the exception message.
     *
     * @param skillName name of the skill to remove from favourites.
     */
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
