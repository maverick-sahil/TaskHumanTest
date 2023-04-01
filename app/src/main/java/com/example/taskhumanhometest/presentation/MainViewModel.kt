package com.example.taskhumanhometest.presentation

import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskhumanhometest.data.remote.SkillsDataDto
import com.example.taskhumanhometest.domain.repository.SkillRepository
import com.example.taskhumanhometest.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Sahil Salgotra on 01/04/23 12:59 PM
 */

// This is the ViewModel responsible for handling the main screen logic, which includes loading skills data, adding and removing favourite skills, and managing the expansion and collapse of skills cards.
// It uses StateFlow to expose the current state of the skills and card list, which can be observed by the view layer.
// The ViewModel receives an instance of the SkillRepository through constructor injection, which it uses to interact with the data layer.
@HiltViewModel
class MainViewModel @Inject constructor(
    private val skillRepository: SkillRepository
) : ViewModel() {

    private val _skillsState = MutableStateFlow(SkillsState())
    val skillsState = _skillsState.asStateFlow()

    private val _revealedCardIdsList = MutableStateFlow(listOf<String>())
    val revealedCardIdsList = _revealedCardIdsList.asStateFlow()

    private val _listHeightSize = MutableStateFlow(IntSize(0, 0))
    val listHeightSize = _listHeightSize.asStateFlow()

    // The loadSkillsData function triggers a network call to fetch the skill data from the repository, and updates the skillsState accordingly.
    fun loadSkillsData() {
        _skillsState.value = SkillsState(
            skillData = null,
            loading = true
        )
        viewModelScope.launch {
            when (val result = skillRepository.getSkillData()) {
                is Resource.Success -> {
                    _skillsState.value = skillsState.value.copy(
                        topic = result.data?.topicHeader?.tileName,
                        skillData = result.data?.skills,
                        loading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    _skillsState.value = skillsState.value.copy(
                        loading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    fun addFavouriteData(skillItem: SkillsDataDto) {
        viewModelScope.launch {
            when (skillRepository.addFavourite(
                skillName = skillItem.tileName,
                dictionaryName = skillItem.dictionaryName
            )) {
                is Resource.Success -> {
                    val updatedState = skillsState.value.skillData?.map {
                        if (it.tileName == skillItem.tileName) {
                            it.copy(isFavorite = true)
                        } else {
                            it
                        }
                    }
                    _skillsState.value = skillsState.value.copy(
                        skillData = updatedState
                    )
                }
                is Resource.Error -> {

                }
            }
        }
    }

    fun removeFavouriteData(skillItem: SkillsDataDto) {
        viewModelScope.launch {
            when (skillRepository.removeFavourite(skillName = skillItem.tileName)) {
                is Resource.Success -> {
                    val updatedState = skillsState.value.skillData?.map {
                        if (it.tileName == skillItem.tileName) {
                            it.copy(isFavorite = false)
                        } else {
                            it
                        }
                    }
                    _skillsState.value = skillsState.value.copy(
                        skillData = updatedState
                    )
                }
                is Resource.Error -> {

                }
            }
        }
    }

    fun onItemExpanded(cardId: String) {
        if (_revealedCardIdsList.value.contains(cardId)) return
        _revealedCardIdsList.value = _revealedCardIdsList.value.toMutableList().also { list ->
            list.add(cardId)
        }
    }

    fun onItemCollapsed(cardId: String) {
        if (!_revealedCardIdsList.value.contains(cardId)) return
        _revealedCardIdsList.value = _revealedCardIdsList.value.toMutableList().also { list ->
            list.remove(cardId)
        }
    }

    fun onItemHeightChanged(newSize: IntSize) {
        _listHeightSize.value = newSize
    }
}