package com.example.loginpage.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginpage.screens.regseterpage.ResgsterPageCompineantion
import com.example.loginpage.utils.Screens




@Composable
fun Nagvation(navController: NavHostController) {

    val navController = navController
    val nagivate = remember<(String) -> Unit> {
        {

            navController.navigate(it){
                popUpTo(it)
            }
        }
    }
    NavHost(navController = navController, startDestination = Screens.Regster.route) {
        composable(route = Screens.Regster.route) {
            ResgsterPageCompineantion(onNavgite = nagivate)
        }

        composable(Screens.Login.route) {
            LogInPage(navController = navController)

        }
        composable(Screens.HomePage.route) {
            HomePage()
        }

    }
}



