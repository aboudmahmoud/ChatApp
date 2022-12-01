package com.example.loginpage.screens.home.helper

sealed class HomeIntnent(){
   data class logOut(val resulte: () -> Unit): HomeIntnent()
}
