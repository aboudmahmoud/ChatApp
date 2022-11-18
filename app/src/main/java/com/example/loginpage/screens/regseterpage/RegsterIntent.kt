package com.example.loginpage.screens.regseterpage

import com.example.loginpage.Moudle.User.UserInfo

sealed class RegsterIntent( val  user: UserInfo){
     class RegsttesUser( user: UserInfo):RegsterIntent(user = user)
}
