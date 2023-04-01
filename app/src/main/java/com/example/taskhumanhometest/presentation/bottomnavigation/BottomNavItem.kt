package com.example.taskhumanhometest.presentation.bottomnavigation

import androidx.annotation.DrawableRes

/**
 * Created by Sahil Salgotra on 01/04/23 2:31 PM
 */
data class BottomNavItem(
    val name: String,
    val route: String,
    @DrawableRes val icon: Int
)
