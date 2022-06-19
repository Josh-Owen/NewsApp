package com.joshowen.newsrepository.retrofit


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val error: Exception? = null): ResultWrapper<Nothing>()
}