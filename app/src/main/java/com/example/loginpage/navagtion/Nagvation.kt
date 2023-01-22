package com.example.loginpage.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.navagtion.viewmodel.NagvitionViewModel
import com.example.loginpage.screens.chatPage.ChatUI
import com.example.loginpage.screens.regseterpage.ResgsterPageCompineantion
import com.example.loginpage.screens.walcome.WalcomePage
import com.example.loginpage.utils.Screens
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterial3Api
@Composable
fun Nagvation(navController: NavHostController,
navagtionViewModle: NagvitionViewModel = hiltViewModel()
) {
    val nagivateWithoutBopup= remember<(String) -> Unit> {
        {
            navController.navigate(it)
        }
    }
    val nagivate = remember<(String) -> Unit> {
        {
            navController.navigate(it){
                popUpTo(0)

            }


        }
    }

    val ngiaateWithData= remember<(Any,String,String)-> Unit> {
        {
            UsereData,Key,Path  ->

            navController.currentBackStackEntry?.savedStateHandle?.set(
                key=Key,
                value = UsereData
            )
            //we Can't Pop up or we lost data
            navController.navigate(Path)
        }
    }


    NavHost(navController = navController, startDestination =navagtionViewModle.StatredDistation ) {
        composable(route = Screens.Regster.route) {
            ResgsterPageCompineantion(onNavgite = nagivate)
        }

        composable(Screens.Login.route) {
            LogInPage(onNaviteWithOutBobUp = nagivateWithoutBopup, onNavgite = nagivate)

        }
        composable(Screens.HomePage.route) {

         HomePage(onNavgite = nagivateWithoutBopup)
        }
        composable(Screens.Walcom.route) {
            WalcomePage( onNavgite=nagivateWithoutBopup)
        }
        composable(Screens.Chat.route) {
            ChatUI()
        }
    }
}





