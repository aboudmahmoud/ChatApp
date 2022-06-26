package com.example.loginpage.Database.SeriveQurey

import com.example.loginpage.Moudle.User.UserInfo

interface SiravseFiucnation {
    fun registerUser(userInfo: UserInfo,
                     result: (UiState<String>) -> Unit)

}