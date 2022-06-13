package com.example.loginpage.Page

sealed class Screens(val route:String)
{

    object Regster1 : Screens("Register1")
    object Regster2 : Screens("Register2")
}
