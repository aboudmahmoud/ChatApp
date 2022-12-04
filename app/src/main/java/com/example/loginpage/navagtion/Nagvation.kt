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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.navagtion.viewmodel.NagvitionViewModel
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
/*    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val obsver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {

            } else if (event == Lifecycle.Event.ON_STOP) {

            }
        }
        lifecycleOwner.lifecycle.addObserver(obsver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(obsver)
        }
    }*/
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

    val nagiveteWithDatause = remember<(String,String,CurrenUserStatis) -> Unit> {
        {
            destantion,key,data->
            navController.popBackStack()
            navController.currentBackStackEntry?.savedStateHandle?.set(
                key=key,
                value = data
            )

            navController.navigate(destantion)
        }
    }

    NavHost(navController = navController, startDestination =navagtionViewModle.StatredDistation ) {
        composable(route = Screens.Regster.route) {
            ResgsterPageCompineantion(onNavgite = nagivate)
        }

        composable(Screens.Login.route) {
            LogInPage(onNavgite = nagivate, onNagivteGetUset = nagiveteWithDatause)

        }
        composable(Screens.HomePage.route) {
            LaunchedEffect(key1 =it , block ={
                val result  = navController.previousBackStackEntry?.savedStateHandle?.get<CurrenUserStatis?>("CurrenUserStatis")
                if (result != null) {
                    if(result.userInfo!=null)navagtionViewModle.userData=result
                }


            } )

            HomePage(onNavgite = nagivate, userInfo = navagtionViewModle.userData!!)
        }
        composable(Screens.Walcom.route) {
            WalcomePage( onNavgite=nagivateWithoutBopup)
        }
    }
}





