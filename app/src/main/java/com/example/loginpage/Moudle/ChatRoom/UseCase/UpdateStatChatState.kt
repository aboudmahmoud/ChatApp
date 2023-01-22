package com.example.loginpage.Moudle.ChatRoom.UseCase


sealed class UpdateStatChatState<out T> {
    object Idel: UpdateStatChatState<Nothing>()
    data class Success<out T>(val data: T): UpdateStatChatState<T>()
    data class Falier(val Errors: String?) : UpdateStatChatState<Nothing>()
}