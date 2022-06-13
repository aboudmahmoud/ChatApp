package com.example.loginpage.Page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Nagvation() {
    val navController= rememberNavController()
    NavHost(navController = navController , startDestination =Screens.Regster1.route){
        composable(route=Screens.Regster1.route){
            RegsterPage1(navController= navController)
        }
        composable(Screens.Regster2.route){
            RegsterPage2()

        }

    }
}