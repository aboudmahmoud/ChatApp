package com.example.loginpage.utils

sealed class Screens(val route:String)
{
    object Regster : Screens("Register")
     object Login : Screens("Login")
    object HomePage: Screens("HomePage")
    object Walcom:Screens("Walcome")
    object Chat:Screens("Chat")
}
