package com.example.loginpage.Moudle.User

data class UserInfo(
    val UserName:String,
    val UserID:String,
    val UserImage:String?=null,
    val UserPassword:String,
    val UserEmail:String,
    val UserDucmentID:String?=null,
)
