package com.example.loginpage.utils.helper

sealed class UiState<out T> {
    object Idel: UiState<Nothing>()
     object Loading: UiState<Nothing>()
    data class Success<out T>(val data: T): UiState<T>()
    data class Failure(val error: String?): UiState<Nothing>()
}
