package com.example.loginpage.utils.helper

sealed class UploadState<out T> {
    object Loading: UploadState<Nothing>()
    data class Success<out T>(val data: T): UploadState<T>()
    data class Failure(val error: String?): UploadState<Nothing>()
}