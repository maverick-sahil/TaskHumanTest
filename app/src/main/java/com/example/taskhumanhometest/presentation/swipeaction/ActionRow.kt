package com.example.taskhumanhometest.presentation.swipeaction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskhumanhometest.R

/**
 * Created by Sahil Salgotra on 01/04/23 5:52 PM
 */
@Composable
fun ActionsRow(
    modifier: Modifier = Modifier, // Optional modifier for the composable
    onFavorite: () -> Unit, // Callback to be invoked when the composable is clicked
    favouriteAction: FavouriteAction // Enum value to determine the favourite action
) {
    Column(
        modifier = modifier
            .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 16.dp) // Apply padding to the Column
            .background(
                color = Color.Red, // Background color of the Column
                shape = RoundedCornerShape(10.dp) // Rounded corners of the Column
            )
            .clickable { onFavorite.invoke() }, // Make the Column clickable and invoke the callback when clicked
        horizontalAlignment = Alignment.CenterHorizontally, // Center the contents horizontally
        verticalArrangement = Arrangement.Center // Center the contents vertically
    ) {
        // Determine the content to be displayed based on the favouriteAction enum value
        val favouriteContent = when (favouriteAction) {
            FavouriteAction.ADD -> Pair(stringResource(R.string.add_favourite), R.drawable.ic_fav_add)
            FavouriteAction.ADDED -> Pair(stringResource(R.string.added), R.drawable.ic_fav_added)
            FavouriteAction.REMOVE -> Pair(stringResource(R.string.remove_favourite), R.drawable.ic_fav_remove)
        }
        // Display an icon using the drawable resource specified in the favouriteContent Pair
        Icon(
            painter = painterResource(id = favouriteContent.second),
            tint = Color.White, // Tint color of the icon
            contentDescription = null, // Content description for the icon (optional)
        )
        Spacer(modifier = Modifier.height(5.dp)) // Add a spacer with a height of 5dp
        // Display the text content using the string resource specified in the favouriteContent Pair
        Text(
            text = favouriteContent.first,
            fontSize = 14.sp, // Font size of the text
            color = Color.White, // Text color
            textAlign = TextAlign.Center // Text alignment
        )
    }
}

// Declare an enum class with three values
enum class FavouriteAction {
    ADD, // Value representing adding to favorites
    ADDED, // Value representing that it has already been added to favorites
    REMOVE // Value representing removing from favorites
}