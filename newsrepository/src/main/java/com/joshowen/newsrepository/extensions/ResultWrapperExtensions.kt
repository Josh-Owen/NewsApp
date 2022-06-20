package com.joshowen.newsrepository.extensions

import com.joshowen.newsrepository.retrofit.ResultWrapper

fun <T> ResultWrapper<T>.isSuccess() : Boolean {
    return this is ResultWrapper.Success
}

fun <T> ResultWrapper<T>.isNetworkError() : Boolean {
    return this is ResultWrapper.NetworkError
}

fun <T> ResultWrapper<T>.isNetworkException() : Boolean {
    return this is ResultWrapper.NetworkException
}