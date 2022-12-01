package com.example.loginpage.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginpage.navagtion.NagvitionViewModel
import com.example.loginpage.screens.regseterpage.ResgsterPageCompineantion
import com.example.loginpage.screens.walcome.WalcomePage
import com.example.loginpage.utils.Screens
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterial3Api
@Composable
fun Nagvation(navController: NavHostController,
navagtionViewModle: NagvitionViewModel= hiltViewModel()
) {

    val nagivateWithoutBopup= remember<(String) -> Unit> {
        {
            navController.navigate(it)
        }
    }
    val nagivate = remember<(String) -> Unit> {
        {
            navController.popBackStack()
            navController.navigate(it)
        }
    }
    NavHost(navController = navController, startDestination =navagtionViewModle.StatredDistation ) {
        composable(route = Screens.Regster.route) {
            ResgsterPageCompineantion(onNavgite = nagivate)
        }

        composable(Screens.Login.route) {
            LogInPage(onNavgite = nagivate)

        }
        composable(Screens.HomePage.route) {
            HomePage(onNavgite = nagivate)
        }
        composable(Screens.Walcom.route) {
            WalcomePage( onNavgite=nagivateWithoutBopup)
        }
    }
}



