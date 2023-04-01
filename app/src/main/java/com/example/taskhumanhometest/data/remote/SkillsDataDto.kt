package com.example.taskhumanhometest.data.remote

data class SkillsDataDto(
    val tileName: String,
    val displayTileName: String,
    val type: String,
    val dictionaryName: String,
    val tileBackground: String,
    val skillIcon: String,
    val availability: AvailabilityDto,
    val moreProvidersAvailable: Boolean,
    val providerInfo: List<ProviderInfoDto>,
    val isFavourite: Boolean,
    val columns: Int
)
