package com.example.taskhumanhometest.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by Sahil Salgotra on 01/04/23 5:34 PM
 */
@Composable
fun ColorIndicator(
    modifier: Modifier = Modifier,
    hexColor: String
) {
    val color = Color(android.graphics.Color.parseColor(hexColor))
    Box(
        modifier = modifier
            .size(14.dp)
            .background(
                color = color,
                shape = CircleShape
            ),
    )
}