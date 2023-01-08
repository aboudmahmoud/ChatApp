package com.example.loginpage.data.SeriveQurey.firesStroe

import android.net.Uri
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.utils.helper.UiState
import com.example.loginpage.utils.helper.UploadState

interface SiravseFiucnation {
    suspend fun uploadSingleFile(fileUri: Uri, onResult: (UploadState<Uri>) -> Unit)
    fun updateUserInfo(user: CurrenUserStatis, result: (UiState<String>) -> Unit)
    fun GetUsetAllUser( result: (UiState<List<CurrenUserStatis>>) -> Unit)

}