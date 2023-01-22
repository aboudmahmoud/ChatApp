package com.example.loginpage.screens.chatPage.Intent

import com.example.loginpage.Moudle.ChatRoom.MessegDiteals

sealed class ChatPageIntnent{
    class SendMessegIntent(val message: MessegDiteals):ChatPageIntnent()
     object MessageSendDOonet:ChatPageIntnent()
    object SetChatIDelWhanMessaeNewAdd:ChatPageIntnent()
    object SetDataMessageIDel:ChatPageIntnent()
}
