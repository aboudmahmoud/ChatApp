package com.example.loginpage.data.SeriveQurey.fireauth

import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.Moudle.User.UserInfo

import com.example.loginpage.utils.helper.UiState

interface Regster {
    fun regsterUser(userMail: CurrenUserStatis, result: (UiState<String>) -> Unit)
    fun updateUserInfo(user: CurrenUserStatis, result: (UiState<String>) -> Unit)
    fun loginUser(userEmail:String,UserPassword:String, result: (UiState<String>) -> Unit)
    fun forgotPassword(UserEmail: String, result: (UiState<String>) -> Unit)
    fun logout(result: () -> Unit)
    fun storeSession(id: String, result: (CurrenUserStatis?) -> Unit)
    fun getSession(result: (CurrenUserStatis?) -> Unit)

}