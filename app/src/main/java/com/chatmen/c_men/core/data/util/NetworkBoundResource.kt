package com.chatmen.c_men.core.data.util

import com.chatmen.c_men.core.presentation.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

inline fun <RequestType, ResultType> networkBoundResource(
    crossinline query: () -> Flow<RequestType>,
    crossinline fetch: suspend () -> ResultType,
    crossinline saveFetchResult: suspend (ResultType) -> Unit,
    crossinline shouldFetch: (RequestType) -> Boolean = { true }
): Flow<Resource<RequestType>> = channelFlow {
    val data = query().first()

    if (shouldFetch(data)) {
        val loading = launch {
            query().collect { send(Resource.Loading(it)) }
        }

        try {
            saveFetchResult(fetch())
            loading.cancel()
            query().collect { send(Resource.Success(it)) }
        } catch (t: Throwable) {
            loading.cancel()
            query().collect {
                send(
                    Resource.Error(
                        uiText = t.localizedMessage?.let { msg -> UiText.Dynamic(msg) }
                            ?: UiText.unknownError(),
                        data = it
                    )
                )
            }
        }
    } else {
        query().collect { send(Resource.Success(it)) }
    }
}