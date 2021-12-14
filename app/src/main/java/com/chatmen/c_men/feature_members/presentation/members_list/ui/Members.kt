package com.chatmen.c_men.feature_members.presentation.members_list.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.chatmen.c_men.core.presentation.components.BackArrowButton
import com.chatmen.c_men.core.presentation.components.TransparentTopAppBar
import com.chatmen.c_men.core.presentation.navigation.Destination
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.core.presentation.util.asString
import com.chatmen.c_men.feature_members.presentation.members_list.MembersEvent
import com.chatmen.c_men.feature_members.presentation.members_list.MembersState
import com.google.accompanist.swiperefresh.SwipeRefresh
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun Members(
    state: MembersState,
    onEvent: (MembersEvent) -> Unit,
    eventsFlow: Flow<BasicUiEvent>,
    navigate: (String) -> Unit,
    navigateUp: () -> Unit
) {
    val events by rememberUpdatedState(newValue = eventsFlow)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        events.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navigate(event.destination)
                }
                is UiEvent.ShowSnackbar -> {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            event.uiText.asString(context)
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TransparentTopAppBar(
                title = stringResource(id = Destination.Members.titleRes),
                navigationIcon = { BackArrowButton(navigateUp) },
                actions = {}
            )
        }
    ) {
        SwipeRefresh(
            state = state.refreshState,
            onRefresh = { onEvent(MembersEvent.Refresh) }
        ) {
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
                            username = member.username,
                            bio = member.bio,
                            profilePictureUrl = member.profilePictureUrl,
                            onClick = { onEvent(MembersEvent.MemberClick(member.username)) }
                        )
                    }
                }
            }
        }
    }
}