package com.pravinkumarp.shadiassignment.extension

sealed class ApiResponse<out T: Any> {
    data class Success<out T: Any>(val data: T): ApiResponse<T>()
    data class Error(val exception: Exception): ApiResponse<Nothing>()
    object Loading: ApiResponse<Nothing>()

    fun toData(): T? = if(this is Success) this.data else null
}