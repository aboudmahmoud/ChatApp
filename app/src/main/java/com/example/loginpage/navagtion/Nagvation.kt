package com.example.loginpage.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginpage.screens.regseterpage.ResgsterPageCompineantion
import com.example.loginpage.utils.Screens




@Composable
fun Nagvation(navController: NavHostController) {

    val navController = navController
    NavHost(navController = navController, startDestination = Screens.Regster.route) {
        composable(route = Screens.Regster.route) {
            ResgsterPageCompineantion()
        }

        composable(Screens.Login.route) {
            LogInPage(navController = navController)

        }
        composable(Screens.HomePage.route) {
            HomePage()
        }

    }
}



