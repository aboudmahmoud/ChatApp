package com.example.loginpage.data.SeriveQurey

import com.example.loginpage.Moudle.User.UserInfo

interface SiravseFiucnation {
    fun registerUser(userInfo: UserInfo,
                     result: (UiState<String>) -> Unit)
}