package com.joshowen.newsrepository.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> callApi(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            val result = apiCall.invoke()
            ResultWrapper.Success(result)
        } catch (throwable: Exception) {
            ResultWrapper.GenericError(throwable)
        }
    }
}