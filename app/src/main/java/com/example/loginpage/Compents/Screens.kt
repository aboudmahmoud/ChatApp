package com.example.loginpage.Compents

sealed class Screens(val route:String)
{

    object Regster1 : Screens("Register1")
    object Regster2 : Screens("Register2")
    object Login : Screens("Login")
    object HomePage : Screens("HomePage")
}
