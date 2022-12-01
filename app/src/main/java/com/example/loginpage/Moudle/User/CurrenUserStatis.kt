package com.example.loginpage.Moudle.User

data class CurrenUserStatis(
    var userInfo: UserInfo?=null,
    var crunntStatus: CurrentOnlieOff = CurrentOnlieOff.Online
)
