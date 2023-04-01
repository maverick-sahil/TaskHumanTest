package com.example.taskhumanhometest.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.example.taskhumanhometest.presentation.swipeaction.ActionsRow
import com.example.taskhumanhometest.presentation.swipeaction.DraggableCard
import com.example.taskhumanhometest.presentation.swipeaction.FavouriteAction
import com.example.taskhumanhometest.presentation.ui.theme.background
import com.example.taskhumanhometest.presentation.ui.theme.textDark
import com.example.taskhumanhometest.presentation.util.LifecycleEvent
import com.example.taskhumanhometest.presentation.util.dp
import com.example.taskhumanhometest.presentation.util.pxToDp

/**
 * Created by Sahil Salgotra on 01/04/23 4:07 PM
 */

const val CARD_OFFSET = 120f

@Composable
fun DiscoverScreen(
    mainViewModel: MainViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    LifecycleEvent(lifecycleOwner, onStart = {
        mainViewModel.loadSkillsData()
    })
    val skillsState = mainViewModel.skillsState.collectAsState()
    val revealedCardIds = mainViewModel.revealedCardIdsList.collectAsState()
    val itemSizeChange = mainViewModel.listHeightSize.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background),
    ) {
        Text(
            text = skillsState.value.topic ?: "",
            color = textDark,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        skillsState.value.skillData?.let { skillsData ->
            LazyColumn(
                content = {
                    items(skillsData) { skillItem ->
                        Box(
                            Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            ActionsRow(
                                modifier = Modifier
                                    .height(itemSizeChange.value.height.pxToDp(LocalContext.current).dp)
                                    .width(170.dp),
                                onFavorite = {
                                    if (!skillItem.isFavorite) {
                                        mainViewModel.addFavouriteData(skillItem)
                                    } else {
                                        mainViewModel.removeFavouriteData(skillItem)
                                    }
                                },
                                favouriteAction = if (skillItem.isFavorite) FavouriteAction.REMOVE else
                                    FavouriteAction.ADD
                            )
                            DraggableCard(
                                skillItem = skillItem,
                                isRevealed = revealedCardIds.value.contains(skillItem.tileName),
                                cardOffset = CARD_OFFSET.dp(),
                                onExpand = { mainViewModel.onItemExpanded(skillItem.tileName) },
                                onCollapse = { mainViewModel.onItemCollapsed(skillItem.tileName) },
                                onSizeChanged = { mainViewModel.onItemHeightChanged(it) }
                            )
                        }
                    }
                })
        }
    }
}
