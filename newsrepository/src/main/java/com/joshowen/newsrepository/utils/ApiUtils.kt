package com.joshowen.newsrepository.utils

import com.joshowen.newsrepository.retrofit.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T> callApi(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> Response<T>): ResultWrapper<Response<T>> {
    return withContext(dispatcher) {
        try {
            val result = apiCall.invoke()
            if(result.isSuccessful) {
                ResultWrapper.Success(result)
            }
            else {
                ResultWrapper.NetworkError(result.message())
            }
        } catch (throwable: Exception) {
            ResultWrapper.NetworkException(throwable)
        }
    }
}