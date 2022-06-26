package com.example.loginpage.Moudle.User

data class CurrenUserStatis(
    val userInfo: UserInfo,
    var crunntStatus: CurrentOnlieOff = CurrentOnlieOff.OffLine
)
