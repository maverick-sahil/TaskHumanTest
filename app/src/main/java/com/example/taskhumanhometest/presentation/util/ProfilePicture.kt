package com.example.taskhumanhometest.presentation.util

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

/**
 * Created by Sahil Salgotra on 01/04/23 4:44 PM
 */
@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier,
    url: String
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier.clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}