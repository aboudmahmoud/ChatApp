package com.example.loginpage.Moudle.ChatRoom.UseCase

sealed class MessegSendState{
    object Idel: MessegSendState()
    object Success:  MessegSendState()
    class Falier(val Errors: String?) : MessegSendState()

}
