package com.example.loginpage.data.SeriveQurey.firesStroe

import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.utils.helper.UiState

interface SiravseFiucnation {

    fun GetUsetAllUser( result: (UiState<List<UserInfo>>) -> Unit)
}