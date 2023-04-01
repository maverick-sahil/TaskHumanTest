package com.example.taskhumanhometest.presentation.swipeaction

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskhumanhometest.R
import com.example.taskhumanhometest.data.remote.AvailabilityDto
import com.example.taskhumanhometest.data.remote.ProviderInfoDto
import com.example.taskhumanhometest.data.remote.SkillsDataDto
import com.example.taskhumanhometest.presentation.ui.theme.textDark
import com.example.taskhumanhometest.presentation.ui.theme.textLight
import com.example.taskhumanhometest.presentation.util.ColorIndicator
import com.example.taskhumanhometest.presentation.util.ProfilePicture
import com.example.taskhumanhometest.presentation.util.relativeDateTime
import kotlin.math.roundToInt

/**
 * Created by Sahil Salgotra on 01/04/23 5:54 PM
 */

// Constants for animation duration and minimum drag amount
const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

/**
Composable function that creates a draggable card with [skillItem] data and [isRevealed] state.
[cardOffset] is the current offset of the card, [onExpand] and [onCollapse] are callbacks for when the
card is expanded or collapsed, and [onSizeChanged] is a callback for when the size of the card changes.
 */
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableCard(
    skillItem: SkillsDataDto,
    isRevealed: Boolean,
    cardOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
    onSizeChanged: (IntSize) -> Unit
) {
    // Remember the current transition state of the card and set its target state
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    // Update the card's transition with the current state and animate its offset
    val transition = updateTransition(transitionState, "cardTransition")
    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) cardOffset else 0f },
    )
    // Create the modifier for the card with the specified padding and offset, and detect horizontal drag gestures
    val modifier = Modifier
        .defaultMinSize(minHeight = 120.dp)
        .padding(4.dp)
        .offset { IntOffset(-offsetTransition.roundToInt(), 0) }
        .pointerInput(Unit) {
            detectHorizontalDragGestures { _, dragAmount ->
                when {
                    dragAmount <= MIN_DRAG_AMOUNT -> onExpand()
                    dragAmount > MIN_DRAG_AMOUNT -> onCollapse()
                }
            }
        }
    // Create the skill card composable with the specified [modifier], [skillItem], and [onSizeChanged] callback
    SkillCard(modifier = modifier, skillItem = skillItem, onSizeChanged)
}

/**
Composable function that creates a card with the specified [modifier] and [skillItem] data, and calls
[onSizeChanged] callback when the size of the card changes.
 */
@Composable
fun SkillCard(
    modifier: Modifier = Modifier,
    skillItem: SkillsDataDto,
    onSizeChanged: (IntSize) -> Unit
) {
    Box(
        modifier = Modifier
            .onSizeChanged { onSizeChanged.invoke(it) }
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            .background(Color.Transparent)
    ) {
        Card(
            backgroundColor = Color.White,
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = skillItem.tileName,
                    modifier = Modifier.align(Alignment.Start),
                    color = textDark,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProviderPicturesView(providerInfo = skillItem.providerInfo)
                    skillItem.availability?.let {
                        AvailabilityView(availability = it)
                    }
                }
            }
        }
        skillItem.availability?.let {
            ColorIndicator(hexColor = it.color)
        }
    }
}

@Composable
fun ProviderPicturesView(providerInfo: List<ProviderInfoDto>?) {
    Row {
        providerInfo?.forEachIndexed { index, info ->
            ProfilePicture(
                url = info.profileImage,
                modifier = Modifier
                    .size(35.dp)
                    .offset((-4 * index).dp, 0.dp)
                    .border(1.dp, Color.White, CircleShape)
            )
        }
    }
}

@Composable
fun AvailabilityView(availability: AvailabilityDto) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "${availability.startTime.relativeDateTime()} - ${availability.endTime.relativeDateTime()}",
            color = textLight,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_time),
            contentDescription = null,
            tint = textLight,
            modifier = Modifier.size(12.dp)
        )
    }
}