package com.example.loginpage.Page

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginpage.Compents.Screens




@Composable
fun Nagvation(navController: NavHostController) {

    val navController = navController
    NavHost(navController = navController, startDestination = Screens.Regster1.route) {
        composable(route = Screens.Regster1.route) {
            RegsterPage1(navController = navController)
        }
        composable(Screens.Regster2.route) {
            RegsterPage2(navController = navController)

        }
        composable(Screens.Login.route) {
            LogInPage(navController = navController)

        }
        composable(Screens.HomePage.route) {
            HomePage()
        }

    }
}



