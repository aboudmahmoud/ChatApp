package com.example.loginpage.data.SeriveQurey.firesStroe

import android.net.Uri
import com.example.loginpage.Moudle.ChatRoom.MessegDiteals
import com.example.loginpage.Moudle.ChatRoom.UseCase.MessegSendState
import com.example.loginpage.Moudle.ChatRoom.UseCase.UpdateStatChatState
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.utils.helper.UiState
import com.example.loginpage.utils.helper.UploadState

interface SiravseFiucnation {
    suspend fun uploadSingleFile(fileUri: Uri, onResult: (UploadState<Uri>) -> Unit)
    fun updateUserInfo(user: CurrenUserStatis, result: (UiState<String>) -> Unit)
    fun GetUsetAllUser( result: (UiState<List<CurrenUserStatis>>) -> Unit)

    fun onChangeUpateMeease(res:(UpdateStatChatState<MessegDiteals>)->Unit)
    fun getRealTimeMessages(result: (UiState<List<MessegDiteals>>) -> Unit)

    fun SendRealTimeMessages(Message:MessegDiteals,res:(MessegSendState)->Unit)

}