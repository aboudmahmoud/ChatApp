package com.example.loginpage.Moudle.User

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
 var UserName:String?=null,
 var UserID:String?=null,

 var UserImage:String?="https://i.pinimg.com/originals/fe/73/96/fe7396f8424945310261cb27ad8ac3a0.jpg",
 var UserDucmentID:String?=null,
):userMail(), Parcelable

