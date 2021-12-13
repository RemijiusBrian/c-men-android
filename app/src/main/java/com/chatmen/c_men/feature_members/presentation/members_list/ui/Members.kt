package com.chatmen.c_men.feature_members.presentation.members_list.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.feature_members.presentation.members_list.MembersEvent
import com.chatmen.c_men.feature_members.presentation.members_list.MembersState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun Members(
    state: MembersState,
    onEvent: (MembersEvent) -> Unit,
    eventsFlow: Flow<BasicUiEvent>,
    navigate: (String) -> Unit
) {
    val events by rememberUpdatedState(newValue = eventsFlow)

    LaunchedEffect(key1 = Unit) {
        events.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navigate(event.destination)
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        state.members.forEach { (separator, members) ->
            item {
                MemberSeparator(
                    separator = separator,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            items(items = members) { member ->
                MemberItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(),
                    name = member.name,
                    bio = member.bio,
                    profilePictureUrl = member.profilePictureUrl,
                    onClick = { onEvent(MembersEvent.MemberClick(member.username)) }
                )
            }
        }
    }
}