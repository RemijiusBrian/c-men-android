package com.chatmen.c_men.feature_members.presentation.members_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatmen.c_men.core.data.util.Resource
import com.chatmen.c_men.core.domain.util.Refresh
import com.chatmen.c_men.core.presentation.navigation.Destination
import com.chatmen.c_men.core.presentation.util.BasicUiEvent
import com.chatmen.c_men.core.presentation.util.UiEvent
import com.chatmen.c_men.core.presentation.util.UiText
import com.chatmen.c_men.feature_members.domain.use_case.MembersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembersViewModel @Inject constructor(
    private val useCases: MembersUseCases,
) : ViewModel() {

    // Refresh Trigger
    private val _refresh = Channel<Refresh>()
    private val refresh = _refresh.receiveAsFlow()

    // State
    private val _state = mutableStateOf(MembersState())
    val state: State<MembersState> = _state

    // Events
    private val _events = Channel<BasicUiEvent>()
    val events = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            _refresh.send(Refresh.NORMAL)
        }
        onEvent(MembersEvent.InitState)
    }

    // On Event
    fun onEvent(event: MembersEvent) {
        when (event) {
            MembersEvent.InitState -> {
                collectMembers()
            }
            is MembersEvent.MemberClick -> {
                onMemberClick(event.member)
            }
            MembersEvent.Refresh -> {
                refresh()
            }
        }
    }

    // Collect Members
    private fun collectMembers() {
        refresh.flatMapLatest { refresh ->
            useCases.getMembers(refresh)
        }.onEach { resource ->
            when (resource) {
                is Resource.Error -> {
                    _events.send(UiEvent.ShowSnackbar(resource.message ?: UiText.unknownError()))
                    _state.value = state.value.copy(
                        members = useCases.groupMembers(resource.data),
                        refreshState = state.value.refreshState.apply {
                            isRefreshing = false
                        }
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        members = useCases.groupMembers(resource.data),
                        refreshState = state.value.refreshState.apply {
                            isRefreshing = true
                        }
                    )
                }
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        members = useCases.groupMembers(resource.data),
                        refreshState = state.value.refreshState.apply {
                            isRefreshing = false
                        }
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    // On Member Click
    private fun onMemberClick(memberUsername: String) = viewModelScope.launch {
        _events.send(UiEvent.Navigate(Destination.Messages.withArgs("-1", memberUsername)))
    }

    // Refresh
    private fun refresh() = viewModelScope.launch {
        _refresh.send(Refresh.FORCE)
    }
}