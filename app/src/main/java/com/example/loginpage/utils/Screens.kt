package com.example.loginpage.utils

sealed class Screens(val route:String)
{

    object Regster : Screens("Register")
     object Regster1 : Screens("Register1")
      object Regster2 : Screens("Register2")
     object Login : Screens("Login")
    object HomePage: Screens("HomePage")
}
