package com.example.loginpage.Moudle.User
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrenUserStatis(
    var userInfo: UserInfo?=UserInfo(),
    var crunntStatus: CurrentOnlieOff = CurrentOnlieOff.Online
):Parcelable
